package com.nhdtech.apps.weathermind.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nhdtech.apps.weathermind.data.local.dao.ForecastDao
import com.nhdtech.apps.weathermind.data.local.entity.ForecastEntity

@Database(version = 1, entities = [ForecastEntity::class])
abstract class ForecastDatabase: RoomDatabase() {
    abstract val dao: ForecastDao
}