package com.example.mausam_weatherapp.API

import com.example.mausam_weatherapp.API.DataClass.Current
import com.example.mausam_weatherapp.API.DataClass.Day
import com.example.mausam_weatherapp.API.DataClass.Forecastday
import com.example.mausam_weatherapp.API.DataClass.WeatherClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val APIKEY = "1bbbe481addd454fa40150903231302"

interface ApiInterface {
    @GET("v1/current.json?key=$APIKEY&days=1&aqi=no&alerts=no")
    fun getWeatherDetails(@Query("q")place : String?): Call<WeatherClass>
    @GET("v1/current.json?key=$APIKEY&days=1&aqi=no&alerts=no")
    fun getAstro(@Query("q")place : String): Call<Day>




}