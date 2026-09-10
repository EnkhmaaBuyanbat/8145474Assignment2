package com.enkhmaa.a8145474assignment2.ui.login

import com.enkhmaa.a8145474assignment2.data.LoginResponse
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
class LoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: TechnologyRepository

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun `initial state is Idle`() {
        assertEquals(LoginUiState.Idle, viewModel.uiState.value)
    }

    @Test
    fun `login with empty credentials returns Error`() {
        viewModel.login("", "")
        assertTrue(viewModel.uiState.value is LoginUiState.Error)
        assertEquals("Username and password cannot be blank", (viewModel.uiState.value as LoginUiState.Error).message)
    }

    @Test
    fun `successful login updates state to Success`() = runTest {
        val expectedKeypass = "tech123"
        `when`(repository.login("8145474", "Enkhmaa")).thenReturn(LoginResponse(expectedKeypass))

        viewModel.login("8145474", "Enkhmaa")

        assertTrue(viewModel.uiState.value is LoginUiState.Success)
        assertEquals(expectedKeypass, (viewModel.uiState.value as LoginUiState.Success).keypass)
    }

    @Test
    fun `failed login updates state to Error`() = runTest {
        val errorMessage = "Invalid credentials"
        `when`(repository.login("wrong", "pass")).thenThrow(RuntimeException(errorMessage))

        viewModel.login("wrong", "pass")

        assertTrue(viewModel.uiState.value is LoginUiState.Error)
        assertEquals(errorMessage, (viewModel.uiState.value as LoginUiState.Error).message)
    }
}
