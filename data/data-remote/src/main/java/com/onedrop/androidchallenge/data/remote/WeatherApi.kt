package com.onedrop.androidchallenge.data.remote

import com.onedrop.androidchallenge.models.remote.WireWeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/onecall?exclude=alerts,hourly,minutely&units=imperial")
    suspend fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Response<WireWeatherData>
}


