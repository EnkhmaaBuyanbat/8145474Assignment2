package com.enkhmaa.a8145474assignment2.repository

import com.enkhmaa.a8145474assignment2.data.DashboardResponse
import com.enkhmaa.a8145474assignment2.data.LoginRequest
import com.enkhmaa.a8145474assignment2.data.LoginResponse
import com.enkhmaa.a8145474assignment2.network.ApiService
import javax.inject.Inject

class TechnologyRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun login(username: String, password: String): LoginResponse {
        val request = LoginRequest(username, password)
        return apiService.login(request)
    }

    suspend fun getDashboard(keypass: String): DashboardResponse {
        return apiService.getDashboard(keypass)
    }
}
