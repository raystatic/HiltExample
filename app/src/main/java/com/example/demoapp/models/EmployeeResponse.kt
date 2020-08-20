package com.example.demoapp.models

data class EmployeeResponse(
    val data: List<Employee>?=null,
    val status: String?=""
)