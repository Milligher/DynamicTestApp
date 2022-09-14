package com.milligher.dynamictestapp.di

import com.milligher.dynamictestapp.domain.repository.RetrofitRepository
import com.milligher.dynamictestapp.domain.useCase.GetConfigurationUseCase
import com.milligher.dynamictestapp.domain.useCase.GetEmployeeUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModel {

    @Provides
    fun provideGetConfigurationUseCase(retrofitRepository: RetrofitRepository): GetConfigurationUseCase {
        return GetConfigurationUseCase(retrofitRepository = retrofitRepository)
    }

    @Provides
    fun provideGetEmployeeUseCase(retrofitRepository: RetrofitRepository): GetEmployeeUseCase {
        return GetEmployeeUseCase(retrofitRepository = retrofitRepository)
    }

}