package com.milligher.dynamictestapp

import android.app.Application
import com.milligher.dynamictestapp.di.AppComponent
import com.milligher.dynamictestapp.di.AppModule
import com.milligher.dynamictestapp.di.DaggerAppComponent
import com.milligher.dynamictestapp.di.api.ApiModule
import com.milligher.dynamictestapp.di.api.DaggerApiComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()


        appComponent = DaggerAppComponent.builder()
            .apiComponent(apiComponent)
            .appModule(AppModule(context = this))
            .build()
    }
}