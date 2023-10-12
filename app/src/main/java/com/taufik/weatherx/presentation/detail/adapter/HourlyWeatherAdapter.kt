package com.taufik.weatherx.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.weatherx.R
import com.taufik.weatherx.data.remote.service.UrlEndpoint
import com.taufik.weatherx.databinding.ItemWeatherHourlyBinding
import com.taufik.weatherx.model.details.onecall.Hourly
import com.taufik.weatherx.utils.epochToTime
import com.taufik.weatherx.utils.loadImage
import kotlin.math.roundToInt

class HourlyWeatherAdapter :
    ListAdapter<Hourly, HourlyWeatherAdapter.HourlyViewHolder>(HOURLY_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder =
        HourlyViewHolder(
            ItemWeatherHourlyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class HourlyViewHolder(private val binding: ItemWeatherHourlyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Hourly) {
            binding.apply {
                tvWeatherTimeHourly.text = epochToTime(data.dt)
                imgWeatherHourly.loadImage(
                    itemView.context,
                    itemView.context.getString(
                        R.string.text_image_icon,
                        UrlEndpoint.WEATHER_BASE_URL,
                        UrlEndpoint.WEATHER_ICON,
                        data.weather.first().icon
                    )
                )

                val weatherDegree = data.temp
                val weatherDegreeInt = weatherDegree.roundToInt()
                val weatherDegreeStr = weatherDegreeInt.toString()
                tvWeatherDegreeHourly.text = String.format(
                    "%s %s",
                    weatherDegreeStr,
                    "\u00B0C"
                )
            }
        }
    }

    companion object {
        val HOURLY_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hourly>() {
            override fun areItemsTheSame(
                oldItem: Hourly,
                newItem: Hourly
            ): Boolean = oldItem.dt == newItem.dt

            override fun areContentsTheSame(
                oldItem: Hourly,
                newItem: Hourly
            ): Boolean = oldItem == newItem
        }
    }
}