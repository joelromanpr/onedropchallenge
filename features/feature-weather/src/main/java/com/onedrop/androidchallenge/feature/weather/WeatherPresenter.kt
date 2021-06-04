package com.onedrop.androidchallenge.feature.weather

import android.util.Log
import com.onedrop.androidchallenge.app.shared.ViewState
import com.onedrop.androidchallenge.data.repo.WeatherRepository
import com.onedrop.androidchallenge.models.application.Coords
import com.onedrop.androidchallenge.models.application.Result
import com.onedrop.androidchallenge.models.application.WeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherPresenter @Inject constructor(
    private val repo: WeatherRepository
) : WeatherContract.WeatherPresenter, CoroutineScope {

    private var view: WeatherContract.View? = null

    private var currentWeatherData: WeatherData? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onDestroy() = try {
        job.cancel()
        view = null
    } catch (e: Exception) {
        Log.e("Presenter.onDestroy", e.toString())
    }

    override fun onViewCreated(view: WeatherContract.View) {
        this.view = view
        Log.d("onViewCreated", "Ready to consume presenter")
    }

    override fun onLocationRequested() {
        view?.onViewStateChanged(ViewState.Loading)
    }

    override fun onLoadWeather(loadType: LoadType) = try {
        currentWeatherData = null
        view?.onViewStateChanged(ViewState.Loading)
        launch {
            val weatherData = when (loadType) {
                is LoadType.NewYork -> repo.loadWeatherForNewYork()
                is LoadType.CurrentLocation -> repo.loadWeather(
                    Coords(
                        loadType.coords.latitude,
                        loadType.coords.longitude
                    )
                )
            }
            when (weatherData) {
                is Result.Success -> {
                    weatherData.data.let { currentWeatherData = it }
                    currentWeatherData?.let { view?.onWeatherDataFound(it) }
                    view?.onViewStateChanged(ViewState.Ready)
                }
                is Result.Error -> {
                    view?.onViewStateChanged(ViewState.Error("Something broke, no worries OneDrop will show a more useful error next time!"))
                }
            }
        }

    } catch (e: Exception) {
        Log.e("onLoadWeatherTapped", e.toString())
    }

    override fun onViewForecastForCurrentRequested() {
        currentWeatherData?.let {
            //track things, this is for KPI's within product usually
            view?.onViewForecastForCurrentRequested(it)
        }
    }
}