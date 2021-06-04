package com.onedrop.androidchallenge.feature.forecast

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.onedrop.androidchallenge.feature.forecast.databinding.ActivityWeatherForecastBinding
import com.onedrop.androidchallenge.models.application.WeatherData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForecastActivity : AppCompatActivity() {


    /**
     * Did not had time to consider,
     * empty cases, better design, create View/Presenter contract in case
     * we want to expand etc
     */

    private var data: WeatherData? = null

    private lateinit var binding: ActivityWeatherForecastBinding
    private lateinit var forecastAdapter: ForecastAdapter

    companion object {
        const val EXTRA_WEATHER_DATA = "weather.data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        binding = ActivityWeatherForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extractData()
        setupForecastList()
    }

    private fun extractData() {
        data = intent.getParcelableExtra(EXTRA_WEATHER_DATA)
        Log.d(WeatherForecastActivity::class.simpleName, "Data from intent:$data")
    }

    private fun setupForecastList() {
        forecastAdapter = ForecastAdapter()
        binding.forecastList.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(this@WeatherForecastActivity)
        }
        forecastAdapter.setData(data?.forecast.orEmpty())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}

