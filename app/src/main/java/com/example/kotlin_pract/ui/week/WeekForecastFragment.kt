package com.example.kotlin_pract.ui.week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlin_pract.R
import com.example.kotlin_pract.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeekForecastFragment : Fragment() {


    private val viewModel: CurrentWeatherViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                WeatherTheme {
                    CurrentWeatherScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun CurrentWeatherScreen(viewModel: CurrentWeatherViewModel) {
    val favoriteLocation by viewModel.favoriteLocation.observeAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (favoriteLocation != null) {
            Text(text = favoriteLocation!!.cityName)
            Text(
                text = stringResource(
                    id = R.string.weather_description,
                    favoriteLocation!!.weatherDescription.capitalize(Locale.current)
                )
            )
            Text(text = stringResource(id = R.string.temperature, favoriteLocation!!.temp))
            Text(text = stringResource(id = R.string.humidity, favoriteLocation!!.humidity))
            Text(text = stringResource(id = R.string.pressure, favoriteLocation!!.pressure))
        } else {
            Text(text = stringResource(id = R.string.favorite_not_found))
        }
    }
}