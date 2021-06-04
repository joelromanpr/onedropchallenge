package com.example.androidchallenge

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.onedrop.androidchallenge.app.shared.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigator: Navigator

    lateinit var rootView: View

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                toHome()
            } else {
                showLocationRationale()
            }
        }

    private fun showLocationRationale() {
        Snackbar.make(
            this,
            rootView,
            "Sorry, we need location access to show you accurate weather information",
            Snackbar.LENGTH_INDEFINITE,
        ).setAction("Retry") { requestLocationAccess() }
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.rootview)

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                toHome()
            }
            else -> {
                requestLocationAccess()
            }
        }
    }

    private fun requestLocationAccess() {
        requestPermissionLauncher.launch(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun toHome() {
        navigator.toWeatherHome(this)
        finish()
    }
}