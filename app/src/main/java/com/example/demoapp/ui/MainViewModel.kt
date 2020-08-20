package com.example.demoapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.models.EmployeeResponse
import com.example.demoapp.other.Resource
import com.example.demoapp.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    private val _res = MutableLiveData<Resource<EmployeeResponse>>()

    val res : LiveData<Resource<EmployeeResponse>>
        get() = _res

    init {
        getEmployees()
    }

    private fun getEmployees()  = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getEmployee().let {
            if (it.isSuccessful){
                _res.postValue(Resource.success(it.body()))
            }else{
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}