package com.onedrop.androidchallenge.feature.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.onedrop.androidchallenge.models.application.Weather

class ForecastVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val weatherInfo: MaterialTextView = itemView.findViewById(R.id.weather_data)

    fun bind(weather: Weather) {
        weatherInfo.text = weather.forecastString()
    }

    companion object {
        @JvmStatic
        fun create(parent: ViewGroup) = ForecastVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_weather_data,
                parent,
                false
            )
        )
    }
}