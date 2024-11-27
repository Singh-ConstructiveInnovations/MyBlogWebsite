package com.example.blogmultiplateform.navigation


/**
 *todo: Created by `Mr Singh` on `26-11-2024` at `22:04`
 */

sealed class Screen(val route: String) {
    data object Home : Screen(route = "home")
    data object Login : Screen(route = "login")
    data object CreateAPost : Screen(route = "create")
    data object MyPost : Screen(route = "posts")
}
