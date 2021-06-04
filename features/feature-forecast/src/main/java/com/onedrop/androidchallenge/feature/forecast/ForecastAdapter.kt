package com.onedrop.androidchallenge.feature.forecast

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedrop.androidchallenge.models.application.Weather

class ForecastAdapter : RecyclerView.Adapter<ForecastVH>() {
    private val data: MutableList<Weather> = mutableListOf()
    fun setData(weatherData: List<Weather>) {
        data.clear()
        data.addAll(weatherData)
        notifyItemRangeInserted(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastVH =
        ForecastVH.create(parent)

    override fun onBindViewHolder(holder: ForecastVH, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size

}