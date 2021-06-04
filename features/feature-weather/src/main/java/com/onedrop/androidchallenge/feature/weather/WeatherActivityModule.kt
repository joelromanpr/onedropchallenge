package com.onedrop.androidchallenge.feature.weather

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class WeatherActivityModule {

    @[Provides]
    fun providePresenter(presenter: WeatherPresenter): WeatherContract.WeatherPresenter =
        presenter

    @[Provides]
    fun provideWeatherContractView(v: WeatherContract.View): WeatherContract.View =
        v
}