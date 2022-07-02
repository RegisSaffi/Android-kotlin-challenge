package com.regis.weather

import com.google.gson.Gson
import com.regis.weather.api.ApiService
import com.regis.weather.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}