package com.onedrop.androidchallenge.app.shared

import android.content.Context
import com.onedrop.androidchallenge.models.application.WeatherData

interface Navigator {
    fun toWeatherHome(context: Context)
    fun toForecastDetails(context: Context, weatherData: WeatherData)
}

