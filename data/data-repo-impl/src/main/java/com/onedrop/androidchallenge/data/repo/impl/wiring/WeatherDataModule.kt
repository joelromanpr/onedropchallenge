package com.onedrop.androidchallenge.data.repo.impl.wiring

import com.onedrop.androidchallenge.data.repo.WeatherRepository
import com.onedrop.androidchallenge.data.repo.impl.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherDataModule {

    @[Binds Singleton]
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}