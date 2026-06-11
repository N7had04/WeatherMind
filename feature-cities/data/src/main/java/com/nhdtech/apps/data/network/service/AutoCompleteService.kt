package com.nhdtech.apps.data.network.service

import com.nhdtech.apps.data.network.dto.CityDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AutoCompleteService {
    @GET("search.json")
    suspend fun searchCities(
        @Query("q") query: String
    ): Response<List<CityDto>>
}