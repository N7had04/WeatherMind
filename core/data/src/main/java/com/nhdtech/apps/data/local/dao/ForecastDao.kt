package com.nhdtech.apps.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhdtech.apps.data.local.entity.ForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentForecast(forecast: ForecastEntity)

    @Query("DELETE FROM forecast WHERE locationName = :locationName")
    suspend fun deleteCurrentForecast(locationName: String)

    @Query("SELECT * FROM forecast ORDER BY isCurrentLocation DESC, sortOrder ASC")
    fun getAllForecasts(): Flow<List<ForecastEntity>>

    @Query("UPDATE forecast SET sortOrder = :order WHERE locationName = :locationName")
    suspend fun updateSortOrder(locationName: String, order: Int)
}