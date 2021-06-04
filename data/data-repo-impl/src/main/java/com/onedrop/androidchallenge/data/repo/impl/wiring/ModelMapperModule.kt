package com.onedrop.androidchallenge.data.repo.impl.wiring


import com.onedrop.androidchallenge.data.repo.ModelMapper
import com.onedrop.androidchallenge.data.repo.impl.WireWeatherDataMapper
import com.onedrop.androidchallenge.models.application.WeatherData
import com.onedrop.androidchallenge.models.remote.WireWeatherData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ModelMapperModule {

    @Provides
    fun bindWireWeatherDataMapper(impl: WireWeatherDataMapper): ModelMapper<WireWeatherData?, WeatherData?> =
        impl

}