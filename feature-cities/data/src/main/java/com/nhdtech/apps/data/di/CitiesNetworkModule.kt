package com.nhdtech.apps.data.di

import com.nhdtech.apps.data.network.service.AutoCompleteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CitiesNetworkModule {
    @Singleton
    @Provides
    fun provideAutoCompleteService(retrofit: Retrofit): AutoCompleteService {
        return retrofit.create(AutoCompleteService::class.java)
    }
}