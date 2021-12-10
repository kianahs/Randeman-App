package com.example.atry

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.home, "Home")
    object Resources : NavigationItem("Resources", R.drawable.analysis, "Resource")
    object Setting : NavigationItem("Setting", R.drawable.settings, "Setting")
    object Account : NavigationItem("Account", R.drawable.account, "Account")
    object FAQ : NavigationItem("FAQ", R.drawable.question, "FAQ")
}

