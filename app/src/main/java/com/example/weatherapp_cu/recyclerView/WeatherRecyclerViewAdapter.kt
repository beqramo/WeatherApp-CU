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


class WeatherRecyclerViewAdapter(val weatherHours : List<WeatherModel>) : RecyclerView.Adapter<WeatherRecyclerViewAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        return WeatherViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_hour_fragment, parent, false)
        )
    }

    override fun getItemCount() = weatherHours.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherHour = weatherHours[position]

        holder.view.termperature.text = weatherHour.main.temp.toString()

        val sdf = SimpleDateFormat("MM/dd/yyyy")

        val netDate = Date( weatherHour.dt.toLong())

        holder.view.hour.text = sdf.format(netDate)

//        Glide.with(holder.view.context)
//            .load("http://openweathermap.org/img/wn/${weatherHour.weather[0].icon}")
//            .into(holder.view.imageView3)
    }




    class WeatherViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}