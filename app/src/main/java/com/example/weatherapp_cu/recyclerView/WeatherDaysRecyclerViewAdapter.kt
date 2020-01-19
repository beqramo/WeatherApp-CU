package com.example.weatherapp_cu.recyclerView


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp_cu.WeatherModel
import com.example.weatherapp_cu.R
import com.soywiz.klock.DateTime
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.weather_days_fragment.view.*


class WeatherDaysRecyclerViewAdapter(val weatherDays : List<WeatherModel>, val weatherNights : List<WeatherModel>) : RecyclerView.Adapter<WeatherDaysRecyclerViewAdapter.WeatherDaysWeatherDays>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDaysWeatherDays {

        return WeatherDaysWeatherDays(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_days_fragment, parent, false)
        )
    }

    override fun getItemCount() = weatherDays.size

    override fun onBindViewHolder(holder: WeatherDaysWeatherDays, position: Int) {
        val weatherDays = weatherDays[position]
        val weatherNight = weatherNights[position]

        holder.view.textView1.text = DateTime.fromUnix(weatherDays.dt.toLong()*1000).format("d MMMM")

        holder.view.textView2.text = weatherDays.main.temp.toString() + " °c"
        Glide.with(holder.view.context)
            .load("https://openweathermap.org/img/wn/${weatherDays.weather[0].icon}.png")
            .into(holder.view.imageView4)

        holder.view.textView33.text = weatherNight.main.temp.toString() + " °c"
        Glide.with(holder.view.context)
            .load("https://openweathermap.org/img/wn/${weatherNight.weather[0].icon}.png")
            .into(holder.view.imageView5)
    }




    class WeatherDaysWeatherDays(val view: View ) : RecyclerView.ViewHolder(view)
}