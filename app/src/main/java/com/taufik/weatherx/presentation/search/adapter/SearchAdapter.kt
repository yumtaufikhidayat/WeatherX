package com.taufik.weatherx.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taufik.weatherx.R
import com.taufik.weatherx.databinding.ItemSearchBinding
import com.taufik.weatherx.model.search.SearchResponseItem

class SearchAdapter(
    private val onItemClickListener: (SearchResponseItem) -> Unit
) : ListAdapter<SearchResponseItem, SearchAdapter.SearchViewHolder>(
    SEARCH_DIFF_CALLBACK
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder =
        SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: SearchViewHolder,
        position: Int
    ) = holder.bind(getItem(position))

    inner class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SearchResponseItem) {
            binding.apply {
                tvCityName.text = data.name
                tvCountryName.text = itemView.context.getString(
                    R.string.text_state_country,
                    data.state,
                    data.country
                )

                clSearch.setOnClickListener {
                    onItemClickListener(data)
                }
            }
        }
    }

    companion object {
        val SEARCH_DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchResponseItem>() {
            override fun areItemsTheSame(
                oldItem: SearchResponseItem,
                newItem: SearchResponseItem
            ): Boolean = oldItem.country == newItem.country

            override fun areContentsTheSame(
                oldItem: SearchResponseItem,
                newItem: SearchResponseItem
            ): Boolean = oldItem == newItem
        }
    }
}