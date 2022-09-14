package com.milligher.dynamictestapp.data.repository

import com.milligher.dynamictestapp.data.server.ServerCommunicator
import com.milligher.dynamictestapp.domain.model.configuration.Configuration
import com.milligher.dynamictestapp.domain.model.employee.Employee
import com.milligher.dynamictestapp.domain.repository.RetrofitRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitRepositoryImpl(private val serverCommunicator: ServerCommunicator) :
    RetrofitRepository {

    override fun getConfiguration(): Single<Configuration>? {
        return serverCommunicator.getConfiguration()
            .flatMap {
                val configuration = it.body()!!
                Single.just(configuration)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getEmployee(employee: String, params: Map<String, String>): Single<Employee>? {
        return serverCommunicator.getEmployee(employee = employee, params = params)
            .flatMap {
                val item = it.body()!!
                Single.just(item)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}