package com.taufik.weatherx.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.weatherx.R
import com.taufik.weatherx.data.remote.service.UrlEndpoint
import com.taufik.weatherx.databinding.ItemWeatherDailyBinding
import com.taufik.weatherx.model.details.daily.DailyItem
import com.taufik.weatherx.utils.epochToDate
import com.taufik.weatherx.utils.getDayName
import com.taufik.weatherx.utils.loadWeatherIcon
import com.taufik.weatherx.utils.toDegree


class DailyWeatherAdapter :
    ListAdapter<DailyItem, DailyWeatherAdapter.DailyViewHolder>(DAILY_WEATHER_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder =
        DailyViewHolder(
            ItemWeatherDailyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class DailyViewHolder(private val binding: ItemWeatherDailyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DailyItem) {
            binding.apply {
                val epochDayDate = data.dt
                val epochDateStr: String = epochToDate(epochDayDate.toLong())
                val epochDayStr: String = getDayName(epochDateStr)
                tvDayName.text = epochDayStr

                imgWeatherDaily.loadWeatherIcon(
                    itemView.context,
                    itemView.context.getString(
                        R.string.text_image_icon,
                        UrlEndpoint.WEATHER_BASE_URL,
                        UrlEndpoint.WEATHER_ICON,
                        data.weather?.first()?.icon
                    )
                )

                tvWeatherMin.text = data.temp?.min?.toDegree()
                tvWeatherMax.text = data.temp?.max?.toDegree()
            }
        }
    }

    companion object {
        val DAILY_WEATHER_DIFF_CALLBACK = object : DiffUtil.ItemCallback<DailyItem>() {
            override fun areItemsTheSame(
                oldItem: DailyItem,
                newItem: DailyItem
            ): Boolean = oldItem.dt == newItem.dt

            override fun areContentsTheSame(
                oldItem: DailyItem,
                newItem: DailyItem
            ): Boolean = oldItem == newItem
        }
    }
}