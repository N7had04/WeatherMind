package com.nhdtech.apps.data.mapper

import com.nhdtech.apps.data.network.dto.CityDto
import com.nhdtech.apps.domain.model.City

fun CityDto.toDomain(): City {
    return City(
        country = country,
        name = name,
        region = region
    )
}