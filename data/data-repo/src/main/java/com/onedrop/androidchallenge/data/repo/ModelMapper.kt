package com.onedrop.androidchallenge.data.repo

interface ModelMapper<InT, OutT> {
    fun map(inType: InT): OutT
}