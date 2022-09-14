package com.milligher.dynamictestapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.milligher.dynamictestapp.domain.useCase.GetConfigurationUseCase
import javax.inject.Inject

class MainActivityViewModelFactory @Inject constructor(
    private val getConfigurationUseCase: GetConfigurationUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(
            getConfigurationUseCase = getConfigurationUseCase
        ) as T
    }
}