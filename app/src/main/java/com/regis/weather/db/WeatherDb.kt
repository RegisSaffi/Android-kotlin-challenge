package com.regis.weather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.regis.weather.model.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
public abstract class WeatherDb : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDb? = null

        fun getDatabase(context: Context): WeatherDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDb::class.java,
                    "weather"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}