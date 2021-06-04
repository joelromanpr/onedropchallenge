package com.onedrop.androidchallenge.models.remote

data class WireWeather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)