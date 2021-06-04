package com.onedrop.androidchallenge.feature.weather

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.onedrop.androidchallenge.app.shared.Navigator
import com.onedrop.androidchallenge.app.shared.ViewState
import com.onedrop.androidchallenge.feature.weather.databinding.ActivityWeatherBinding
import com.onedrop.androidchallenge.models.application.Coords
import com.onedrop.androidchallenge.models.application.WeatherData
import com.onedrop.androidchallenge.util.extensions.disable
import com.onedrop.androidchallenge.util.extensions.enable
import com.onedrop.androidchallenge.util.extensions.gone
import com.onedrop.androidchallenge.util.extensions.show
import com.yayandroid.locationmanager.LocationManager
import com.yayandroid.locationmanager.configuration.Configurations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_main.view.actions_container
import kotlinx.android.synthetic.main.content_main.view.progress_circular
import kotlinx.android.synthetic.main.content_main.view.weather_data_container
import kotlinx.android.synthetic.main.content_main.view.weather_mylocation_button
import kotlinx.android.synthetic.main.content_main.view.weather_ny_button
import javax.inject.Inject


@AndroidEntryPoint
class WeatherActivity : AppCompatActivity(), WeatherContract.View, LocationListener,
    com.yayandroid.locationmanager.listener.LocationListener {

    @Inject
    internal lateinit var presenter: WeatherContract.WeatherPresenter

    @Inject
    internal lateinit var navigator: Navigator

    private lateinit var binding: ActivityWeatherBinding

    private lateinit var locationManager: LocationManager

    private val locationConfiguration = Configurations.silentConfiguration()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        locationManager = LocationManager.Builder(applicationContext)
            .activity(this)
            .configuration(locationConfiguration)
            .notify(this)
            .build()

        presenter.onViewCreated(this)
        binding.root.setOnClickListener { presenter.onViewForecastForCurrentRequested() }
        binding.root.weather_ny_button.setOnClickListener { presenter.onLoadWeather(LoadType.NewYork) }
        binding.root.weather_mylocation_button.setOnClickListener { requestLocation() }

    }

    private fun requestLocation() {
        locationManager.get()
    }

    override fun onWeatherDataFound(data: WeatherData) {
        binding.root.weather_data_container.text = data.currentWeather.toString()
        Log.d("Data", data.toString())
    }

    override fun onViewForecastForCurrentRequested(data: WeatherData) {
        navigator.toForecastDetails(this, data)
    }

    override fun onViewStateChanged(state: ViewState) {
        when (state) {
            is ViewState.Loading -> {
                binding.root.disable()
                binding.root.weather_data_container.text = ""
                binding.root.weather_data_container.gone()
                binding.root.progress_circular.show()
            }
            is ViewState.Ready -> {
                binding.root.enable()
                binding.root.progress_circular.gone()
                binding.root.actions_container.enable()
                binding.root.weather_data_container.show()
            }
            is ViewState.Error -> {
                binding.root.enable()
                binding.root.progress_circular.gone()
                binding.root.actions_container.enable()
                binding.root.weather_data_container.gone()
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onProcessTypeChanged(processType: Int) {
    }

    override fun onLocationChanged(location: Location) {
        presenter.onLoadWeather(
            LoadType.CurrentLocation(
                Coords(
                    location.latitude,
                    location.longitude
                )
            )
        )
    }

    override fun onLocationFailed(type: Int) {
    }

    override fun onPermissionGranted(alreadyHadPermission: Boolean) {
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }
}