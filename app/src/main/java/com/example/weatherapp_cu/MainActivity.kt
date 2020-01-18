package com.example.weatherapp_cu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp_cu.recyclerView.WeatherRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.hide()

        fetchMovies()
    }

    private fun fetchMovies(){

        WeatherAPI()
            .getWeather().enqueue(object : Callback<WeatherServiceDTO> {
                override fun onFailure(call: Call<WeatherServiceDTO>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<WeatherServiceDTO>,
                    response: Response<WeatherServiceDTO>
                ) {
                    Toast.makeText(applicationContext,response.body().toString(), Toast.LENGTH_LONG).show()

                    val weather = response.body()

                    weather?.let {

                        Region.text = "Tbilisi"


                        recyclerViewWeatherHour.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL ,false)
                        recyclerViewWeatherHour.adapter = WeatherRecyclerViewAdapter(it.list.take(5))
                    }

                }
            })
    }
}
