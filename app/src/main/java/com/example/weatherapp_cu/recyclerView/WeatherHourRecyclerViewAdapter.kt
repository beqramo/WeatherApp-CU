package com.example.weatherapp_cu.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp_cu.WeatherModel
import com.example.weatherapp_cu.R
import kotlinx.android.synthetic.main.weather_hour_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*


class WeatherHourRecyclerViewAdapter(val weatherHours : List<WeatherModel>) : RecyclerView.Adapter<WeatherHourRecyclerViewAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        return WeatherViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_hour_fragment, parent, false)
        )
    }

    override fun getItemCount() = 5

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherHour = weatherHours[position]

        holder.view.termperature.text = weatherHour.main.temp.toString() + " °c"

        val sdf = SimpleDateFormat("hh:mm a")

        val netDate = Date( weatherHour.dt.toLong()* 1000)

        holder.view.hour.text = sdf.format(netDate)

        Glide.with(holder.view.context)
            .load("https://openweathermap.org/img/wn/${weatherHour.weather[0].icon}.png")
            .into(holder.view.imageView3)
    }




    class WeatherViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}