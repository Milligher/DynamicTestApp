package com.milligher.dynamictestapp.di

import com.milligher.dynamictestapp.data.repository.RetrofitRepositoryImpl
import com.milligher.dynamictestapp.data.server.ServerCommunicator
import com.milligher.dynamictestapp.domain.repository.RetrofitRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideAppRepository(serverCommunicator: ServerCommunicator): RetrofitRepository {
        return RetrofitRepositoryImpl(serverCommunicator = serverCommunicator)
    }
}