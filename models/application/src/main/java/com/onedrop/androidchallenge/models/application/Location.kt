package com.onedrop.androidchallenge.models.application

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(val timezone: String, val coords: Coords) : Parcelable