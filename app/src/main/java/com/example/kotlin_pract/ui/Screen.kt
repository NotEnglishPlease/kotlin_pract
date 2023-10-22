package com.example.kotlin_pract.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.kotlin_pract.R

sealed class Screen(
    open val route: String,
    @StringRes open val stringResourceId: Int
) {
    sealed class BottomBarScreen(
        override val route: String,
        @StringRes override val stringResourceId: Int,
        @DrawableRes val unselectedIconResourceId: Int,
        @DrawableRes val selectedIconResourceId: Int
    ) : Screen(route, stringResourceId) {
        data object Home : BottomBarScreen(
            route = "home",
            stringResourceId = R.string.home_screen_title,
            unselectedIconResourceId = R.drawable.home_outlined_24dp,
            selectedIconResourceId = R.drawable.home_filled_24dp
        )

        data object Favorite : BottomBarScreen(
            route = "favorite",
            stringResourceId = R.string.favorite_screen_title,
            unselectedIconResourceId = R.drawable.baseline_favorite_border_24,
            selectedIconResourceId = R.drawable.baseline_favorite_24
        )

        data object CurrentWeather : BottomBarScreen(
            route = "current_weather",
            stringResourceId = R.string.current_weather_screen_title,
            unselectedIconResourceId = R.drawable.cloud_outlined_24dp,
            selectedIconResourceId = R.drawable.cloud_filled_24dp
        )
    }
}