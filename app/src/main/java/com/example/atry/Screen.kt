package com.example.atry

sealed class Screen(val route: String){
    object loginScreen : Screen("login_screen")
    object featuresScreen : Screen("features_screen")
    object seasonsScreen : Screen("seasons_screen")
    object resourcesScreen : Screen("resources_screen")
    object accountFormScreen : Screen("accountForm_screen")
    object resourceFormScreen : Screen("resourceForm_screen")
    object tasksScreen : Screen("tasks_screen")
    object taskFormScreen: Screen("taskForm_screen")
    object registerScreen: Screen("register_screen")
    object contributorScreen: Screen("contributor_screen")
    object FAQScreen: Screen("FAQ_screen")
    object announcementScreen: Screen("announcement_screen")

    fun withArgs(vararg args: String?): String{

        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }



    }



}
