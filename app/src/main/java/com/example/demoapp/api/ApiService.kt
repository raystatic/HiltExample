package com.example.demoapp.api

import com.example.demoapp.models.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{

    @GET("employees")
    suspend fun getEmployees():Response<EmployeeResponse>


}