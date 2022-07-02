package com.regis.weather.repository

import com.regis.weather.api.ApiService
import com.regis.weather.model.Weather
import kotlinx.coroutines.flow.Flow

class WeatherRepository

constructor(private val apiService: ApiService) {
    suspend fun getWeather() = apiService.getWeather(0.0,0.0)
}