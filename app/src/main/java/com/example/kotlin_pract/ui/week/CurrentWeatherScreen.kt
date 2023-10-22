package com.example.kotlin_pract.ui.week

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.example.kotlin_pract.R

@Composable
fun CurrentWeatherScreen(viewModel: CurrentWeatherViewModel) {
    val favoriteLocation by viewModel.favoriteLocation.observeAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (favoriteLocation != null) {
            Text(text = favoriteLocation!!.cityName, style = typography.headlineLarge)
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.weather_description,
                        favoriteLocation!!.weatherDescription.capitalize(Locale.current)
                    )
                )
                Text(text = stringResource(id = R.string.temperature, favoriteLocation!!.temp))
                Text(text = stringResource(id = R.string.humidity, favoriteLocation!!.humidity))
                Text(text = stringResource(id = R.string.pressure, favoriteLocation!!.pressure))
            }
        } else {
            Text(text = stringResource(id = R.string.favorite_not_found))
        }
    }
}