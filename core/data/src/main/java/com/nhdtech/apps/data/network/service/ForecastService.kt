package com.nhdtech.apps.data.network.service

import com.nhdtech.apps.data.network.dto.ForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") location: String,
        @Query("days") days: Int = 3,
        @Query("aqi") aqi: String = "yes",
        @Query("alerts") alerts: String = "no"
    ): Response<ForecastDto>
}