package com.example.kotlin_pract.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlin_pract.data.db.toCityUi

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel
) {
    val cities by viewModel.cities.observeAsState()
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(cities ?: emptyList()) {
            CityItem(
                modifier = Modifier.fillMaxWidth(),
                cityUiState = it.toCityUi(),
                onFavoriteButtonClick = viewModel::setFavoriteCity
            )
        }
    }
}

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    cityUiState: CityUiState,
    onFavoriteButtonClick: (CityUiState) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(8.dp)
    ) {
        Text(text = cityUiState.name)
        IconButton(
            onClick = { onFavoriteButtonClick(cityUiState) }
        ) {
            Icon(
                if (cityUiState.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null
            )
        }
    }
}