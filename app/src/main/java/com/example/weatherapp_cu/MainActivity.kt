package com.example.weatherapp_cu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weatherapp_cu.recyclerView.WeatherRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather_hour_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private fun showWeatherHour (it : WeatherServiceDTO) {

        Region.text = "Tbilisi, Georgia"

        temperature.text = it.list[0].main.temp.toString()
        WeatherMainDescription.text = it.list[0].weather[0].description

        Glide.with(applicationContext)
            .load("https://openweathermap.org/img/wn/${it.list[0].weather[0].icon}.png")
            .into(imageView2)

        recyclerViewWeatherHour.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL ,false)
        recyclerViewWeatherHour.adapter = WeatherRecyclerViewAdapter(it.list.take(5))
    }

}
