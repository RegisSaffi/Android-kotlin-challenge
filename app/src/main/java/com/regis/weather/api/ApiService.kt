package com.regis.weather.api

import com.regis.weather.model.Weather
import com.regis.weather.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.BASE_URL+"weather?appid=${Constants.APP_ID}&units=metric")
    suspend fun getWeather(@Query("lat") lat:Double, @Query("lon") lon:Double ): Response<Weather>

}