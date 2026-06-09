package com.nhdtech.apps.data.local.converter

import androidx.room.TypeConverter
import com.nhdtech.apps.data.local.entity.ForecastDay
import com.nhdtech.apps.data.local.entity.ForecastHour
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ForecastConverters {

    @TypeConverter
    fun fromForecastDayList(
        value: List<ForecastDay>
    ): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toForecastDayList(
        value: String
    ): List<ForecastDay> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromForecastHourList(
        value: List<ForecastHour>
    ): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toForecastHourList(
        value: String
    ): List<ForecastHour> {
        return Json.decodeFromString(value)
    }
}