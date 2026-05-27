package com.nhdtech.apps.weathermind.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhdtech.apps.weathermind.data.local.entity.ForecastEntity

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentForecast(forecast: ForecastEntity)

    @Delete
    suspend fun deleteCurrentForecast(forecast: ForecastEntity)

    @Query("SELECT * FROM forecast")
    suspend fun getAllForecasts(): List<ForecastEntity>
}