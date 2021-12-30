package com.example.atry
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.atry.NavigationItem
import com.example.atry.R
import com.example.atry.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Resources,
        NavigationItem.Account,
        NavigationItem.FAQ
    )
    BottomNavigation(

        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title,modifier = Modifier.size(21.dp)) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    if(item.route == NavigationItem.Home.route ){
                        navController.navigate(Screen.featuresScreen.withArgs("ss"))
                    }
                    if(item.route == NavigationItem.Resources.route ){
                        navController.navigate(Screen.resourcesScreen.withArgs("Resources"))
                    }
                    if(item.route == NavigationItem.Account.route ){
                        navController.navigate(Screen.accountFormScreen.withArgs("mm"))
                    }
                    if(item.route == NavigationItem.FAQ.route ){
                        navController.navigate(Screen.FAQScreen.route)
                    }
//                    navController.navigate(item.route) {
//
//                        // Pop up to the start destination of the graph to
//                        // avoid building up a large stack of destinations
//                        // on the back stack as users select items
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        // Avoid multiple copies of the same destination when
//                        // reselecting the same item
//                        launchSingleTop = true
//                        // Restore state when reselecting a previously selected item
//                        restoreState = true
//                    }
                }
            )
        }
    }
}
