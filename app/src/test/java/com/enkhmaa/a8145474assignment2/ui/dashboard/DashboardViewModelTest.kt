package com.enkhmaa.a8145474assignment2.ui.dashboard

import com.enkhmaa.a8145474assignment2.data.DashboardResponse
import com.enkhmaa.a8145474assignment2.data.Technology
import com.enkhmaa.a8145474assignment2.repository.TechnologyRepository
import com.enkhmaa.a8145474assignment2.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: TechnologyRepository

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = DashboardViewModel(repository)
    }

    @Test
    fun `initial state is Loading`() {
        assertEquals(DashboardUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `successful fetch updates state to Success`() = runTest {
        val entities = listOf(
            Technology("Device1", "Brand1", "OS1", 2021, "Desc1"),
            Technology("Device2", "Brand2", "OS2", 2022, "Desc2")
        )
        val response = DashboardResponse(entities, 2)
        val keypass = "technology"

        `when`(repository.getDashboard(keypass)).thenReturn(response)

        viewModel.fetchDashboard(keypass)

        assertTrue(viewModel.uiState.value is DashboardUiState.Success)
        val successState = viewModel.uiState.value as DashboardUiState.Success
        assertEquals(entities, successState.entities)
        assertEquals(2, successState.total)
    }

    @Test
    fun `failed fetch updates state to Error`() = runTest {
        val errorMessage = "Network Error"
        val keypass = "technology"
        `when`(repository.getDashboard(keypass)).thenThrow(RuntimeException(errorMessage))

        viewModel.fetchDashboard(keypass)

        assertTrue(viewModel.uiState.value is DashboardUiState.Error)
        assertEquals(errorMessage, (viewModel.uiState.value as DashboardUiState.Error).message)
    }
}
