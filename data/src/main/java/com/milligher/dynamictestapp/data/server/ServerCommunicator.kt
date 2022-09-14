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

    companion object {
        private val DEFAULT_TIMEOUT = 10
        private val DEFAULT_RETRY_ATTEMPTS = 4L
    }

    fun getEmployee(employee: String, params: Map<String, String>): Single<Response<Employee>> {
        return mService.getEmployee(employee = employee, params = params)
//            .compose(singleTransformer())
//            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message!!) }
    }

    fun getConfiguration(): Single<Response<Configuration>> {
        return mService.getConfiguration()
    }

    private fun <T> singleTransformer(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }

    private fun <T> observableTransformer(): ObservableTransformer<T, T> = ObservableTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }
}