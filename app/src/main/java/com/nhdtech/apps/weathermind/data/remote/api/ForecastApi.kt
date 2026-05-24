package com.nhdtech.apps.weathermind.data.remote.api

import com.nhdtech.apps.weathermind.BuildConfig
import com.nhdtech.apps.weathermind.data.remote.dto.ForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("/forecast.json")
    suspend fun getForecast(
        @Query("q") location: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("days") days: Int = 3,
        @Query("aqi") aqi: String = "yes",
        @Query("alerts") alerts: String = "no"
    ): Response<ForecastDto>
}