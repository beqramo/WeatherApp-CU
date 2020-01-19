package com.example.weatherapp_cu

data class WeatherModel (
    val dt: Int,
    val main: WeatherMain,
    val weather : List<Weather>,
    val city : City,
    val dt_txt : String

)



data class WeatherMain (
    val temp: Float

)


data class Weather (
    val description: String,
    val main: String,
    val icon: String
)


data class City (
    val name: String
)