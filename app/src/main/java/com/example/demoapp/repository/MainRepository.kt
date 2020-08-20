package com.example.demoapp.repository

import com.example.demoapp.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper:ApiHelper
){

    suspend fun getEmployee() = apiHelper.getEmployees()

}