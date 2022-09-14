package com.milligher.dynamictestapp.domain.useCase

import com.milligher.dynamictestapp.domain.model.configuration.Configuration
import com.milligher.dynamictestapp.domain.repository.RetrofitRepository
import io.reactivex.Single

class GetConfigurationUseCase(private val retrofitRepository: RetrofitRepository) {

    fun execute(): Single<Configuration>? {
        return retrofitRepository.getConfiguration()
    }
}