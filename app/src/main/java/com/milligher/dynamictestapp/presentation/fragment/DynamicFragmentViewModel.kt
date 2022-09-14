package com.milligher.dynamictestapp.presentation.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milligher.dynamictestapp.domain.model.employee.Employee
import com.milligher.dynamictestapp.domain.useCase.GetEmployeeUseCase

class DynamicFragmentViewModel(private val getEmployeeUseCase: GetEmployeeUseCase): ViewModel() {

    private val employeeLiveData = MutableLiveData<Employee>()

    @SuppressLint("CheckResult")
    fun getEmployee(employee: String, params: Map<String, String>) {
        getEmployeeUseCase.execute(employee = employee, params = params)?.subscribe { it -> employeeLiveData.value = it }
    }

    fun getEmployeeLiveDataItem(): LiveData<Employee> {
        return employeeLiveData
    }
}