package com.nhdtech.apps.home.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.nhdtech.apps.home.data.local.datastore.HomePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDataStoreModule {
    @Provides
    @Singleton
    fun provideHomePreferences(
        dataStore: DataStore<Preferences>
    ): HomePreferences {
        return HomePreferences(dataStore)
    }
}