package com.milligher.dynamictestapp.di

import com.milligher.dynamictestapp.di.api.ApiComponent
import com.milligher.dynamictestapp.presentation.MainActivity
import com.milligher.dynamictestapp.presentation.fragment.DynamicFragment
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, DomainModel::class], dependencies = [ApiComponent::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(dynamicFragment: DynamicFragment)
}