package com.milligher.dynamictestapp.data.server

import android.util.Log
import com.milligher.dynamictestapp.domain.model.configuration.Configuration
import com.milligher.dynamictestapp.domain.model.employee.Employee
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit

class ServerCommunicator(private val mService: ApiService) {

    fun getEmployee(employee: String, params: Map<String, String>): Single<Response<Employee>> {
        return mService.getEmployee(employee = employee, params = params)
    }

    fun getConfiguration(): Single<Response<Configuration>> {
        return mService.getConfiguration()
    }
}