package com.example.test.presentation


sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Fav : Screens("fav")
    object Products : Screens("products")
}