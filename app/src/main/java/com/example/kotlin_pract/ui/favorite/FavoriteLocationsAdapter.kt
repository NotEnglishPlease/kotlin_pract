package com.example.kotlin_pract.ui.favorite

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_pract.data.City
import com.example.kotlin_pract.databinding.CityListItemBinding

class FavoriteLocationsAdapter(
    private val onFavoriteButtonClick: (City) -> Unit,
    private val favoriteIcon: Drawable?,
    private val notFavoriteIcon: Drawable?,
) : ListAdapter<City, FavoriteLocationsAdapter.CityViewHolder>(CityDiffUtil) {
    class CityViewHolder(
        private val binding: CityListItemBinding,
        private val onFavoriteButtonClick: (City) -> Unit,
        private val favoriteIcon: Drawable?,
        private val notFavoriteIcon: Drawable?,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.apply {
                cityNameTextView.text = city.name
                if (favoriteIcon != null && notFavoriteIcon != null) {
                    favoriteButton.icon = if (city.isFavorite) favoriteIcon else notFavoriteIcon
                }
                favoriteButton.setOnClickListener {
                    onFavoriteButtonClick(city)
                }
            }
        }
    }

    companion object CityDiffUtil : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            binding = CityListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onFavoriteButtonClick = onFavoriteButtonClick,
            favoriteIcon,
            notFavoriteIcon
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(city = getItem(position))
    }

}