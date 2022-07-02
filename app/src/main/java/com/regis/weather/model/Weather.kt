package com.regis.weather.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather")
data class Weather (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @SerializedName("name")
    var cityName: String,
    @Embedded
    @SerializedName("main")
    var main:Main,
    @Embedded
    @SerializedName("wind")
    var wind:Wind,
    @Embedded
    @SerializedName("sys")
    var sys:Sys,
    @Embedded
    @SerializedName("weather")
    var subWeather:List<SubWeather>,
    @Embedded
    @SerializedName("coord")
    var coord:Coord,
    @SerializedName("dt")
    var dt:Long
)


data class Main(
    @SerializedName("temp")
    var temperature:Double,
    @SerializedName("temp_min")
    var minTemperature:Double,
    @SerializedName("temp_max")
    var maxTemperature:Double,
    @SerializedName("humidity")
    var humidity:Double,
    @SerializedName("pressure")
    var pressure:Double,
)

data class Wind(
    @SerializedName("speed")
    var speed:Double,
    @SerializedName("deg")
    var deg:Double,
)

data class SubWeather(
    @SerializedName("main")
    var main:String,
    @SerializedName("description")
    var description:String,
    @SerializedName("icon")
    var icon:String,

)

data class Sys(
    @SerializedName("sunrise")
    var sunrise:Int,
    @SerializedName("sunset")
    var sunset:Int,
    @SerializedName("country")
    var country:String,
)

data class Coord(
    @SerializedName("lat")
    var lat:Double,
    @SerializedName("lon")
    var lon:Double,

)