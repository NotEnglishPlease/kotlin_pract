package com.example.kotlin_pract.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_pract.ui.favorite.FavoriteScreen
import com.example.kotlin_pract.ui.favorite.FavoriteViewModel
import com.example.kotlin_pract.ui.main.HomeScreen
import com.example.kotlin_pract.ui.main.HomeViewModel
import com.example.kotlin_pract.ui.theme.WeatherTheme
import com.example.kotlin_pract.ui.week.CurrentWeatherScreen
import com.example.kotlin_pract.ui.week.CurrentWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomBarScreens = listOf(
            Screen.BottomBarScreen.Home,
            Screen.BottomBarScreen.Favorite,
            Screen.BottomBarScreen.CurrentWeather
        )


        setContent {
            WeatherTheme {
                var currentScreenState by remember { mutableStateOf<Screen.BottomBarScreen>(Screen.BottomBarScreen.Home) }

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = stringResource(id = currentScreenState.stringResourceId)
                                )
                            }
                        )
                    },
                    bottomBar = {
                        NavigationBar {
                            bottomBarScreens.forEach { screen ->
                                val isSelected =
                                    currentDestination?.hierarchy?.any { it.route == screen.route }
                                        ?: false
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = {
                                        navController.navigate(screen.route)
                                        currentScreenState = screen
                                    },
                                    alwaysShowLabel = false,
                                    icon = {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(
                                                id = if (isSelected) {
                                                    screen.selectedIconResourceId
                                                } else {
                                                    screen.unselectedIconResourceId
                                                }
                                            ),
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(text = stringResource(id = screen.stringResourceId)) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.BottomBarScreen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.BottomBarScreen.Home.route) {
                            val homeViewModel = hiltViewModel<HomeViewModel>()
                            HomeScreen(viewModel = homeViewModel)
                        }

                        composable(Screen.BottomBarScreen.Favorite.route) {
                            val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
                            FavoriteScreen(viewModel = favoriteViewModel)
                        }

                        composable(Screen.BottomBarScreen.CurrentWeather.route) {
                            val currentWeatherViewModel = hiltViewModel<CurrentWeatherViewModel>()
                            CurrentWeatherScreen(viewModel = currentWeatherViewModel)
                        }
                    }
                }
            }
        }
    }
}

