package com.example.demoapp.api

import com.example.demoapp.models.EmployeeResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getEmployees():Response<EmployeeResponse>

}