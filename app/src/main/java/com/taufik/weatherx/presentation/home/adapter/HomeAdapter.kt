package com.taufik.weatherx.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.weatherx.R
import com.taufik.weatherx.data.remote.service.UrlEndpoint
import com.taufik.weatherx.databinding.ItemCityWeatherBinding
import com.taufik.weatherx.model.details.weather.DetailWeathersResponse
import com.taufik.weatherx.utils.loadWeatherIcon
import com.taufik.weatherx.utils.toDegree

class HomeAdapter(
    private val onItemClickListener: (DetailWeathersResponse) -> Unit
) : ListAdapter<DetailWeathersResponse, HomeAdapter.HomeViewHolder>(
    HOME_DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            ItemCityWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class HomeViewHolder(private val binding: ItemCityWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: DetailWeathersResponse) {
                binding.apply {
                    tvCityName.text = data.name
                    imgWeatherIcon.loadWeatherIcon(
                        itemView.context,
                        itemView.context.getString(
                            R.string.text_image_icon,
                            UrlEndpoint.WEATHER_BASE_URL,
                            UrlEndpoint.WEATHER_ICON,
                            data.weather.first().icon
                        )
                    )

                    tvWeatherDegree.text = data.main.temp.toDegree()
                    clCityWeather.setOnClickListener {
                        onItemClickListener(data)
                    }
                }
            }
    }

    companion object {
        val HOME_DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailWeathersResponse>() {
            override fun areItemsTheSame(
                oldItem: DetailWeathersResponse,
                newItem: DetailWeathersResponse
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: DetailWeathersResponse,
                newItem: DetailWeathersResponse
            ): Boolean = oldItem == newItem
        }
    }
}