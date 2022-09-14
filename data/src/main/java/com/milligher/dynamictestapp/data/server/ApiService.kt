package com.milligher.dynamictestapp.data.server

import com.milligher.dynamictestapp.domain.model.configuration.Configuration
import com.milligher.dynamictestapp.domain.model.employee.Employee
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {

    @GET("vth2k/7f41b5d6d3b78838d35a9b504d21f2f0/raw/3e7f17e2cea043ad5d4179da7b884163c34f51d1/test.json")
    fun getConfiguration(): Single<Response<Configuration>>

    @GET("vth2k/7f41b5d6d3b78838d35a9b504d21f2f0/raw/{employee}")
    fun getEmployee(@Path("employee") employee: String?,
                    @QueryMap params: Map<String, String>
    ): Single<Response<Employee>>
}