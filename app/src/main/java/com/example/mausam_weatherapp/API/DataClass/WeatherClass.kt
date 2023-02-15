package com.example.mausam_weatherapp.API.DataClass

data class WeatherClass(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)