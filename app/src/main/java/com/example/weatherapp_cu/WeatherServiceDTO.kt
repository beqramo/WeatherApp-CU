package com.example.weatherapp_cu

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface WeatherAPI {

    @GET("forecast?q=Tbilisi&units=metric&APPID=d000ab3e979b6a58b813549a0fe15272")
    fun getWeather() : Call<WeatherServiceDTO>

    companion object {
        operator fun invoke() : WeatherAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create( WeatherAPI::class.java )
        }
    }
}

data class WeatherServiceDTO(
    val list: List<WeatherModel>
)