package com.example.atry

sealed class Screen(val route: String){
    object loginScreen : Screen("login_screen")
    object featuresScreen : Screen("features_screen")
    object resourcesScreen : Screen("recources_screen")

    fun withArgs(vararg args: String): String{

        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }



    }



}
