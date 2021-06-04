package com.onedrop.androidchallenge.models.remote

data class WireDailyWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val feels_like: Double,
    val humidity: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val pressure: Int,
    val weather: List<WireWeather>
)