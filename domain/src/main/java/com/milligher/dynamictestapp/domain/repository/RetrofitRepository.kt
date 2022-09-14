package com.milligher.dynamictestapp.domain.repository

import com.milligher.dynamictestapp.domain.model.configuration.Configuration
import com.milligher.dynamictestapp.domain.model.employee.Employee
import io.reactivex.Single

interface RetrofitRepository {
    fun getConfiguration(): Single<Configuration>?

    fun getEmployee(employee: String, params: Map<String, String>): Single<Employee>?
}