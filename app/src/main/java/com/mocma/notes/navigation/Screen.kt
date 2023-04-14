package com.mocma.notes.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Note: Screen("note_screen")
}
