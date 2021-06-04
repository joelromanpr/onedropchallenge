package com.onedrop.androidchallenge.data.remote.impl

import com.google.gson.Gson
import com.onedrop.androidchallenge.data.remote.WeatherApi
import com.onedrop.androidchallenge.data.remote.impl.interceptors.KeysInterceptor
import com.onedrop.androidchallenge.data.remote.impl.interceptors.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideCallConverterFactory(
        gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    fun providesOkhttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder().apply {
        addInterceptor(LoggingInterceptor)
        addInterceptor(KeysInterceptor)
        retryOnConnectionFailure(true)
    }

    @Provides
    fun provideRetrofitBuilder(
        converterFactory: Converter.Factory
    ): Retrofit.Builder = Retrofit.Builder().apply {
        addConverterFactory(converterFactory)
        validateEagerly(BuildConfig.DEBUG)
    }

    @Provides
    fun weatherApi(
        okhttpBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder
    ): WeatherApi = retrofitBuilder.apply {
        baseUrl(BuildConfig.OPEN_WEATHER_MAP_BASE_URL)
        client(okhttpBuilder.build())
    }.build().create(WeatherApi::class.java)
}
