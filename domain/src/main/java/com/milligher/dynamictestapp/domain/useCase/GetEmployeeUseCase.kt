package com.milligher.dynamictestapp.domain.useCase

import com.milligher.dynamictestapp.domain.model.employee.Employee
import com.milligher.dynamictestapp.domain.repository.RetrofitRepository
import io.reactivex.Single

class GetEmployeeUseCase(private val retrofitRepository: RetrofitRepository) {

    fun execute(employee: String, params: Map<String, String>): Single<Employee>? {
        return retrofitRepository.getEmployee(employee = employee, params = params)
    }
}