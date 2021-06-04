package com.onedrop.androidchallenge.data.repo.impl


import com.onedrop.androidchallenge.data.remote.WeatherApi
import com.onedrop.androidchallenge.data.repo.ModelMapper
import com.onedrop.androidchallenge.data.repo.WeatherRepository
import com.onedrop.androidchallenge.models.application.Coords
import com.onedrop.androidchallenge.models.application.Result
import com.onedrop.androidchallenge.models.application.WeatherData
import com.onedrop.androidchallenge.models.remote.WireWeatherData
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val wireWeatherDataMapper: ModelMapper<WireWeatherData?, WeatherData?>
) : WeatherRepository {
    override suspend fun loadWeatherForNewYork(): Result<WeatherData?> =
        loadWeather(Coords(40.712776, -74.005974))


    override suspend fun loadWeather(coords: Coords): Result<WeatherData?> = try {
        val response =
            weatherApi.getWeatherForecast(coords.latitude.toString(), coords.longitude.toString())
        val weatherData = wireWeatherDataMapper.map(response.body())

        Result.Success(weatherData)
    } catch (e: Exception) {
        Result.Error(Throwable(e)) //or en-rich further
    }
}