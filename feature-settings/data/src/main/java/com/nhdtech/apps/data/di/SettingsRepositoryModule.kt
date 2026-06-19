package com.nhdtech.apps.data.di

import com.nhdtech.apps.data.local.datastore.AppPreferences
import com.nhdtech.apps.data.repository.SettingsRepositoryImpl
import com.nhdtech.apps.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SettingsRepositoryModule {
    @Singleton
    @Provides
    fun provideSettingsRepository(
        appPreferences: AppPreferences
    ): SettingsRepository {
        return SettingsRepositoryImpl(appPreferences)
    }
}