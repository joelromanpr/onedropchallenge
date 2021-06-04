package com.onedrop.androidchallenge.data.repo

import com.onedrop.androidchallenge.models.application.Coords
import com.onedrop.androidchallenge.models.application.WeatherData
import com.onedrop.androidchallenge.models.application.Result

interface WeatherRepository {
    suspend fun loadWeatherForNewYork(): Result<WeatherData?>
    suspend fun loadWeather(coordinates: Coords): Result<WeatherData?>
}