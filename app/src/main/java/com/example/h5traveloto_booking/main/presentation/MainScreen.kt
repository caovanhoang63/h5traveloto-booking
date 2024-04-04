package com.example.h5traveloto_booking.main.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.main.presentation.ChatScreen.ChatScreen
import com.example.h5traveloto_booking.main.presentation.HomeScreen.HomeScreen
import com.example.h5traveloto_booking.main.presentation.SettingScreen.SettingsScreen
import com.example.h5traveloto_booking.navigate.Screens


data class BottomNavigationItem(
    val title : String,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector,
    val hasNews : Boolean,
    val route : String,
    val badgeCount : Int? = null

)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ScaffoldExample(
    navController: NavController
) {
    val mainNavController = rememberNavController()
    var presses by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Default.Home,
            hasNews = false,
            route = Screens.HomeScreen.name
        ),
        BottomNavigationItem(
            title = "Chat",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Default.Email,
            hasNews = false,
            route = Screens.ChatScreen.name

        ),
        BottomNavigationItem(
            title = "Setting",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Default.Settings,
            hasNews = false,
            route = Screens.SettingScreen.name

        )
    )
    var selectedItemIndex by rememberSaveable { (mutableIntStateOf(0)) }

    Scaffold(
        bottomBar = {
                    NavigationBar (
                    ) {
                        items.forEachIndexed{index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index ,
                                onClick =  {
                                    selectedItemIndex = index
                                    mainNavController.navigate(item.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true

                                    }

                                },
                                alwaysShowLabel = false,

                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if(item.badgeCount != null ) {
                                                Badge {
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            } else if (item.hasNews) {
                                                Badge()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if(index == selectedItemIndex )
                                                item.selectedIcon
                                            else
                                                item.unselectedIcon
                                            ,
                                            contentDescription = item.title
                                        )
                                    }
                                }
                            )
                        }
                    }
        },

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(Modifier.height(2.dp))

            NavHost (
                navController = mainNavController,
                startDestination = Screens.HomeScreen.name,

            ) {
                composable(route = Screens.HomeScreen.name) {
                    HomeScreen(navController)
                }
                composable(route = Screens.ChatScreen.name) {
                    ChatScreen(navController)
                }
                composable(route = Screens.SettingScreen.name) {
                    SettingsScreen(navController)
                }
            }
        }
    }
}

