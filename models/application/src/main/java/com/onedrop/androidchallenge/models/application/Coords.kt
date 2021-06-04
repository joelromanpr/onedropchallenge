package com.onedrop.androidchallenge.models.application

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coords(val latitude: Double, val longitude: Double) : Parcelable