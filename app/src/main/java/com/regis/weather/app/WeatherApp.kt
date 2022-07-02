package com.regis.weather.app

import android.app.Application
import com.regis.weather.db.WeatherDb
import com.regis.weather.repository.DbRepository

class WeatherApp : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { WeatherDb.getDatabase(this) }
    val repository by lazy { DbRepository(database.weatherDao()) }
}