package com.nhdtech.apps.weathermind.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nhdtech.apps.weathermind.data.local.converter.ForecastConverters
import com.nhdtech.apps.weathermind.data.local.dao.ForecastDao
import com.nhdtech.apps.weathermind.data.local.entity.ForecastDay
import com.nhdtech.apps.weathermind.data.local.entity.ForecastEntity
import com.nhdtech.apps.weathermind.data.local.entity.ForecastHour

@Database(version = 1, entities = [ForecastEntity::class, ForecastDay::class, ForecastHour::class])
@TypeConverters(ForecastConverters::class)
abstract class ForecastDatabase: RoomDatabase() {
    abstract val dao: ForecastDao
}