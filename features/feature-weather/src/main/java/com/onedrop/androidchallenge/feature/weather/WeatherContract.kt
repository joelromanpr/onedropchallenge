package com.onedrop.androidchallenge.feature.weather

import com.onedrop.androidchallenge.app.shared.BasePresenter
import com.onedrop.androidchallenge.app.shared.BaseView
import com.onedrop.androidchallenge.models.application.Coords
import com.onedrop.androidchallenge.models.application.WeatherData

interface WeatherContract {
    interface WeatherPresenter : BasePresenter {
        fun onViewCreated(view: View)
        fun onLocationRequested()
        fun onLoadWeather(loadType: LoadType): Any
        fun onViewForecastForCurrentRequested()
    }

    interface View : BaseView<WeatherPresenter> {
        fun onWeatherDataFound(data: WeatherData)
        fun onViewForecastForCurrentRequested(data: WeatherData)
    }
}

sealed class LoadType {
    object NewYork : LoadType()
    data class CurrentLocation(val coords: Coords) : LoadType()
}