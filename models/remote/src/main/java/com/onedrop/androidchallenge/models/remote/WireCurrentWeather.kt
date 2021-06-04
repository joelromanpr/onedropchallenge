package com.onedrop.androidchallenge.models.remote

data class WireCurrentWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    val feels_like: Double,
    val humidity: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val pressure: Int,
    val weather: List<WireWeather>
)


data class WireForecastWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: WireTemps,
    val feels_like: WireTemps, //feels like an api design flaw or maybe we want more object oriented approaches
    val humidity: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val pressure: Int,
    val weather: List<WireWeather>
)


data class WireTemps(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)
