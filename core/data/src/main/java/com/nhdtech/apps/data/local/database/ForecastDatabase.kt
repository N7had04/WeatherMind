package com.nhdtech.apps.data.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nhdtech.apps.data.local.converter.ForecastConverters
import com.nhdtech.apps.data.local.dao.ForecastDao
import com.nhdtech.apps.data.local.entity.ForecastDay
import com.nhdtech.apps.data.local.entity.ForecastEntity
import com.nhdtech.apps.data.local.entity.ForecastHour

@Database(
    version = 5,
    entities = [ForecastEntity::class, ForecastDay::class, ForecastHour::class],
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5)
    ]
)
@TypeConverters(ForecastConverters::class)
abstract class ForecastDatabase: RoomDatabase() {
    abstract val dao: ForecastDao
}