package com.onedrop.androidchallenge.models.application

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Weather(
    val location: Location,
    val temp: Double,
    val humidity: Int,
    val feelsLike: Double,
    val windSpeed: Double,
    val windDegrees: Int,
    val pressure: Int
) : Parcelable {

    fun forecastString(): String {
        return "Temperature: $temp\nHumidity: $humidity\nFeels like: $feelsLike\nWind Speed and degrees: $windSpeed|$windDegrees\nPressure:$pressure"

    }

    override fun toString(): String {
        return "Location: ${location.timezone}\nTemperature: $temp\nHumidity: $humidity\nFeels like: $feelsLike\nWind Speed and degrees: $windSpeed|$windDegrees\nPressure:$pressure"
    }
}

/**
Location (i.e. New York, etc)
Temperature
Humidity
Feels like
Wind speed/degrees
Pressure
 */