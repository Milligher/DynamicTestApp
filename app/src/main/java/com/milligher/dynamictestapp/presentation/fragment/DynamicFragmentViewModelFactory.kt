package com.milligher.dynamictestapp.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.milligher.dynamictestapp.domain.useCase.GetEmployeeUseCase
import javax.inject.Inject

class DynamicFragmentViewModelFactory @Inject constructor(private val getEmployeeUseCase: GetEmployeeUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DynamicFragmentViewModel(
            getEmployeeUseCase = getEmployeeUseCase
        ) as T
    }
}