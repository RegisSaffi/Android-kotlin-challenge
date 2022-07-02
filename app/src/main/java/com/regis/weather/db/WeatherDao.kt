package com.regis.weather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.regis.weather.model.Weather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather ORDER BY id DESC")
    fun getAllRecords(): Flow<List<Weather>>

    @Query("SELECT * FROM weather ORDER BY id DESC limit 1")
    fun getLatest(): Flow<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weather: Weather)

    @Query("DELETE FROM weather")
    suspend fun deleteAll()
}