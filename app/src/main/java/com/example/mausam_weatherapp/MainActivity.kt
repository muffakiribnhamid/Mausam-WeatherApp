package com.example.mausam_weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.RoundedCorner
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.mausam_weatherapp.API.DataClass.Astro
import com.example.mausam_weatherapp.API.DataClass.Day
import com.example.mausam_weatherapp.API.DataClass.Forecastday
import com.example.mausam_weatherapp.API.DataClass.WeatherClass
import com.example.mausam_weatherapp.API.RetrofitInstance

import com.example.mausam_weatherapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var place: String = "Kulgam"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getLocation()
        getAstro()


        binding.btnSearch.setOnClickListener {
            place = binding.country.text.toString()
            getLocation()
            getAstro()
        }







        supportActionBar!!.hide()


    }

    private fun getLocation() {

        RetrofitInstance.apiInterface.getWeatherDetails(place)
            .enqueue(object : Callback<WeatherClass> {
                override fun onResponse(
                    call: Call<WeatherClass>,
                    response: Response<WeatherClass>
                ) {
                    if (response.body() == null) {
                        Toast.makeText(
                            this@MainActivity,
                            "Error Region Not Available",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (response.body() != null) {
                        binding.tvCity.text = "${response.body()!!.location.name},${response.body()!!.location.country}"
                        binding.tvTemp.text = "${response.body()!!.current.temp_c}°"
                        binding.humidity.text = response.body()!!.current.humidity.toString()
                        binding.condition.text = response.body()!!.current.condition.text.toString()
                        binding.uv.text = response.body()!!.current.uv.toString()
                        binding.time.text = response.body()!!.current.last_updated
                        binding.iconmain.load("https:${response.body()!!.current.condition.icon}") {
                            crossfade(true)
                            crossfade(400)
                            transformations(RoundedCornersTransformation(10f))
                        }
                    }


                }

                override fun onFailure(call: Call<WeatherClass>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error Check Your Connection",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })


    }

    private fun getAstro() {

        RetrofitInstance.apiInterface.getAstro(place).enqueue(object : Callback<Day> {
            override fun onResponse(call: Call<Day>, response: Response<Day>) {

                if (response.body() == null) {
                    Toast.makeText(this@MainActivity, "Region Not Available", Toast.LENGTH_SHORT)
                        .show()
                } else if (response.body() != null) {
                    binding.rainpercent.text = "${response.body()!!.daily_chance_of_snow}%"
                    binding.snowpercent.text = "${response.body()!!.daily_chance_of_rain}%"
                }

            }

            override fun onFailure(call: Call<Day>, t: Throwable) {

            }
        })
    }
}