package com.onedrop.androidchallenge.data.remote.impl.interceptors

import com.onedrop.androidchallenge.data.remote.impl.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


/**
 * A Kotlin singleton object which intercepts each network call and allows us to attach
 * API keys to all requests
 */
object KeysInterceptor : Interceptor {
    const val PARAM_KEY = "appid"

    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url.newBuilder()
            .addQueryParameter(PARAM_KEY, BuildConfig.OPEN_WEATHER_API_KEY)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}
