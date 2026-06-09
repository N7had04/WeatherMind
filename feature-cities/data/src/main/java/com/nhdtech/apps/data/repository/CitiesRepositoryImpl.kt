package com.nhdtech.apps.data.repository

import com.nhdtech.apps.data.local.dao.ForecastDao
import com.nhdtech.apps.data.mapper.toDomain
import com.nhdtech.apps.data.mapper.toEntity
import com.nhdtech.apps.data.network.service.ForecastService
import com.nhdtech.apps.domain.model.WeatherForecast
import com.nhdtech.apps.domain.repository.CitiesRepository
import com.nhdtech.apps.domain.util.Resource

class CitiesRepositoryImpl(
    private val forecastDao: ForecastDao
): CitiesRepository {
    override suspend fun deleteForecastFromDb(forecast: WeatherForecast) {
        forecastDao.deleteCurrentForecast(forecast.toEntity())
    }
}