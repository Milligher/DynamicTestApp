package com.milligher.dynamictestapp.di.api

import com.milligher.dynamictestapp.data.server.ServerCommunicator
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
}