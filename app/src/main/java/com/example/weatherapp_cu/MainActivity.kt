package com.example.weatherapp_cu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weatherapp_cu.recyclerView.WeatherDaysRecyclerViewAdapter
import com.example.weatherapp_cu.recyclerView.WeatherHourRecyclerViewAdapter
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.hide()

        fetchWeather()

    }

    private fun fetchWeather(){

        WeatherAPI()
            .getWeather().enqueue(object : Callback<WeatherServiceDTO> {

                override fun onFailure(call: Call<WeatherServiceDTO>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message+"asdfasdf", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<WeatherServiceDTO>,
                    response: Response<WeatherServiceDTO>
                ) {
//                    Toast.makeText(applicationContext,response.body().toString(), Toast.LENGTH_LONG).show()

                    val weather = response.body()

                    weather?.let {
                        showWeatherHour(it)
                    }

                }
            })
    }

    private fun showWeatherHour (value : WeatherServiceDTO) {

        Region.text = "Tbilisi, Georgia"

        temperature.text = value.list[0].main.temp.toString()
        WeatherMainDescription.text = value.list[0].weather[0].description

        Glide.with(applicationContext)
            .load("https://openweathermap.org/img/wn/${value.list[0].weather[0].icon}.png")
            .into(imageView2)

        recyclerViewWeatherHour.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL ,false)
        recyclerViewWeatherHour.adapter = WeatherHourRecyclerViewAdapter(value.list.take(5))


        var days  = listOf<WeatherModel>()
        var nights  = listOf<WeatherModel>()

        nights = value.list.filter {
            val date = DateTime.fromUnix(it.dt.toLong()*1000)
            date.hours == 0
        }
        days = value.list.filter {
            val date = DateTime.fromUnix(it.dt.toLong()*1000)
            date.hours == 12
        }

        recyclerViewWeatherDays.layoutManager = LinearLayoutManager(applicationContext)
        recyclerViewWeatherDays.adapter = WeatherDaysRecyclerViewAdapter(days, nights)
    }

}
