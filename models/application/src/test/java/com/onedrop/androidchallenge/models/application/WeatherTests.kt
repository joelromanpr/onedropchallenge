package com.onedrop.androidchallenge.models.application

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * [Weather] Model unit tests focusing on exposed helper functions.
 *
 */
class WeatherTests {
    @Test
    fun `WHEN calling forecastString() RETURNS expected value`() {
        val weather = Weather(
            Location("timezone", Coords(0.0, 0.0)),
            0.0,
            0,
            0.0,
            0.0,
            0,
            0
        )
        val expected =
            with(weather) { "Temperature: $temp\nHumidity: $humidity\nFeels like: $feelsLike\nWind Speed and degrees: $windSpeed|$windDegrees\nPressure:$pressure" }
        assertEquals(expected, weather.forecastString())
    }

    @Test
    fun `WHEN calling toString() RETURNS expected value`() {
        val weather = Weather(
            Location("timezone", Coords(0.0, 0.0)),
            0.0,
            0,
            0.0,
            0.0,
            0,
            0
        )
        val expected =
            with(weather) { "Location: ${location.timezone}\nTemperature: $temp\nHumidity: $humidity\nFeels like: $feelsLike\nWind Speed and degrees: $windSpeed|$windDegrees\nPressure:$pressure" }
        assertEquals(expected, weather.toString())
    }
}