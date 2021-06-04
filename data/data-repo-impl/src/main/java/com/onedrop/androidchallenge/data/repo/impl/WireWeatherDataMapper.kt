package com.onedrop.androidchallenge.data.repo.impl

import com.onedrop.androidchallenge.models.application.Coords
import com.onedrop.androidchallenge.models.application.Location
import com.onedrop.androidchallenge.models.application.Weather
import com.onedrop.androidchallenge.models.application.WeatherData
import com.onedrop.androidchallenge.models.remote.WireWeatherData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WireWeatherDataMapper @Inject constructor() :
    com.onedrop.androidchallenge.data.repo.ModelMapper<WireWeatherData?, WeatherData?> {
    override fun map(inType: WireWeatherData?): WeatherData? = inType?.let {
        WeatherData(
            currentWeather = Weather(
                location = Location(it.timezone, Coords(it.lat, it.lon)),
                temp = it.current.temp,
                humidity = it.current.humidity,
                feelsLike = it.current.feels_like,
                windSpeed = it.current.wind_speed,
                windDegrees = it.current.wind_deg,
                pressure = it.current.pressure
            ),
            forecast = it.daily.map { forecast ->
                Weather(
                    location = Location(it.timezone, Coords(it.lat, it.lon)),
                    temp = forecast.temp.day,
                    humidity = forecast.humidity,
                    feelsLike = forecast.feels_like.day,
                    windSpeed = forecast.wind_speed,
                    windDegrees = forecast.wind_deg,
                    pressure = forecast.pressure
                )
            }
        )
    }
}