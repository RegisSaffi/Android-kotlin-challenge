package com.regis.weather.repository

import androidx.annotation.WorkerThread
import com.regis.weather.db.WeatherDao
import com.regis.weather.model.Weather
import kotlinx.coroutines.flow.Flow

class DbRepository(private val weatherDao: WeatherDao) {

    val allRecords: Flow<List<Weather>> = weatherDao.getAllRecords()
    val latest: Flow<List<Weather>> = weatherDao.getLatest()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weather:Weather) {
        weatherDao.insert(weather)
    }
}