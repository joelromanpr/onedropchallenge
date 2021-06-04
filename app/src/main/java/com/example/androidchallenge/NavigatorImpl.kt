package com.example.androidchallenge

import android.content.Context
import android.content.Intent
import com.onedrop.androidchallenge.app.shared.Navigator
import com.onedrop.androidchallenge.feature.forecast.WeatherForecastActivity
import com.onedrop.androidchallenge.feature.weather.WeatherActivity
import com.onedrop.androidchallenge.models.application.WeatherData
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator {
    override fun toWeatherHome(context: Context) {
        context.startActivity(
            Intent(
                context,
               WeatherActivity::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun toForecastDetails(context: Context, weatherData: WeatherData) {
        context.startActivity(
            Intent(
                context,
                WeatherForecastActivity::class.java
            ).apply {
                putExtra(
                    WeatherForecastActivity.EXTRA_WEATHER_DATA,
                    weatherData
                )
            }
        )
    }
}
