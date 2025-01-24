package com.example.showex.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddShow : Screen("add_show")
    object EditShow : Screen("edit_show/{showId}") {
        fun createRoute(showId: String) = "edit_show/$showId"
    }
    object ShowDetails : Screen("show_details/{showId}") {
        fun createRoute(showId: String) = "show_details/$showId"
    }
} 