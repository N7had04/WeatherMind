package com.nhdtech.apps.data.di

import android.app.Application
import androidx.room.Room
import com.nhdtech.apps.data.local.database.ForecastDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideForecastDatabase(app: Application): ForecastDatabase {
        return Room.databaseBuilder(
            app,
            ForecastDatabase::class.java,
            "forecast_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideForecastDao(db: ForecastDatabase) = db.dao
}
