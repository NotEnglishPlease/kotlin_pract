package com.example.kotlin_pract.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_pract.R
import com.example.kotlin_pract.data.db.toCityUi
import com.example.kotlin_pract.databinding.FragmentFavoriteLocationsBinding
import com.example.kotlin_pract.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteLocationsFragment : Fragment() {
    private val viewModel: FavoriteLocationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                WeatherTheme {
                    FavoriteLocationsScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
private fun FavoriteLocationsScreen(
    viewModel: FavoriteLocationsViewModel
) {
    val cities by viewModel.cities.observeAsState()
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(cities ?: emptyList()) {
            CityItem(
                modifier = Modifier.fillMaxWidth(),
                cityUi = it.toCityUi(),
                onFavoriteButtonClick = viewModel::setFavoriteCity
            )
        }
    }
}

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    cityUi: CityUi,
    onFavoriteButtonClick: (CityUi) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(8.dp)
    ) {
        Text(text = cityUi.name)
        IconButton(
            onClick = { onFavoriteButtonClick(cityUi) }
        ) {
            Icon(
                if (cityUi.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null
            )
        }
    }
}