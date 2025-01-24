package com.example.showex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.showex.ui.ShowViewModel
import com.example.showex.ui.screens.*

@Composable
fun ShowNavHost(
    navController: NavHostController,
    viewModel: ShowViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val uiState by viewModel.uiState.collectAsState()
            HomeScreen(
                shows = uiState.shows,
                onAddShow = {
                    navController.navigate(Screen.AddShow.route)
                },
                onShowClick = { show ->
                    navController.navigate(Screen.ShowDetails.createRoute(show.id))
                }
            )
        }

        composable(Screen.AddShow.route) {
            AddEditShowScreen(
                onSave = { show ->
                    viewModel.addShow(show)
                    navController.popBackStack()
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.ShowDetails.route,
            arguments = listOf(navArgument("showId") { type = NavType.StringType })
        ) { backStackEntry ->
            val showId = backStackEntry.arguments?.getString("showId") ?: return@composable
            val show by viewModel.getShowById(showId).collectAsState(initial = null)
            
            show?.let { showEntity ->
                ShowDetailsScreen(
                    show = showEntity,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onEditShow = {
                        navController.navigate(Screen.EditShow.createRoute(showId))
                    },
                    onDeleteShow = {
                        viewModel.deleteShow(showEntity)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            route = Screen.EditShow.route,
            arguments = listOf(navArgument("showId") { type = NavType.StringType })
        ) { backStackEntry ->
            val showId = backStackEntry.arguments?.getString("showId") ?: return@composable
            val show by viewModel.getShowById(showId).collectAsState(initial = null)
            
            show?.let { showEntity ->
                AddEditShowScreen(
                    show = showEntity,
                    onSave = { updatedShow ->
                        viewModel.updateShow(updatedShow)
                        navController.popBackStack()
                    },
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
} 