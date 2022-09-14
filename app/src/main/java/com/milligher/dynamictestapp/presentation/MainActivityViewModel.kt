package com.milligher.dynamictestapp.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milligher.dynamictestapp.domain.model.configuration.Configuration
import com.milligher.dynamictestapp.domain.model.employee.Employee
import com.milligher.dynamictestapp.domain.useCase.GetConfigurationUseCase
import com.milligher.dynamictestapp.domain.useCase.GetEmployeeUseCase

class MainActivityViewModel(
    private val getConfigurationUseCase: GetConfigurationUseCase
): ViewModel()  {

    private val configurationLiveData = MutableLiveData<Configuration>()

    @SuppressLint("CheckResult")
    fun getConfiguration() {
        getConfigurationUseCase.execute()?.subscribe { it -> configurationLiveData.value = it }
    }

    fun getConfigurationLiveDataItem(): LiveData<Configuration> {
        return configurationLiveData
    }

}