package com.onedrop.androidchallenge.util.extensions

import android.view.View

fun View.disable() = kotlin.run { this.isEnabled = false }
fun View.enable() = kotlin.run { this.isEnabled = true }
fun View.gone() = kotlin.run { this.visibility = View.GONE }
fun View.show() = kotlin.run { this.visibility = View.VISIBLE }
