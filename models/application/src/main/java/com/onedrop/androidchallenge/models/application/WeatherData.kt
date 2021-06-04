package com.onedrop.androidchallenge.models.application

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherData(
    val currentWeather: Weather,
    val forecast: List<Weather>
) : Parcelable