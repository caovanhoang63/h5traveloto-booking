package com.example.h5traveloto_booking.main.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.account.AccountScreen
import com.example.h5traveloto_booking.main.presentation.favorite.FavoriteScreen
import com.example.h5traveloto_booking.main.presentation.history.HistoryScreen
import com.example.h5traveloto_booking.main.presentation.home.HomeScreen
import com.example.h5traveloto_booking.main.presentation.schedule.ScheduleScreen
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
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val mainNavController = rememberNavController()
    var presses by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Default.Home,
            unselectedIcon =Icons.Filled.Home,
            hasNews = false,
            route = Screens.HomeScreen.name
        ),
        BottomNavigationItem(
            title = "Schedule",
            selectedIcon = Icons.Default.PermContactCalendar,
            unselectedIcon = Icons.Filled.PermContactCalendar,
            hasNews = false,
            route = Screens.ScheduleScreen.name

        ),
        /*BottomNavigationItem(
            title = "History",
            selectedIcon = Icons.Default.History,
            unselectedIcon = Icons.Filled.History,
            hasNews = false,
            route = Screens.HistoryScreen.name

        ),*/
        BottomNavigationItem(
            title = "Favorites",
            selectedIcon = Icons.Default.Favorite,
            unselectedIcon = Icons.Filled.Favorite,
            hasNews = false,
            route = Screens.FavoriteScreen.name
        ),
        BottomNavigationItem(
            title = "Account",
            selectedIcon = Icons.Default.Person,
            unselectedIcon = Icons.Filled.Person,
            hasNews = false,
            route = Screens.AccountScreen.name
        )
    )
    var selectedItemIndex by rememberSaveable { (mutableIntStateOf(0)) }

    Scaffold(
        bottomBar = {
                    NavigationBar (
                        modifier = Modifier.height(72.dp)
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
                composable(route = Screens.ScheduleScreen.name) {
                    ScheduleScreen(navController)
                }
                composable(route= Screens.FavoriteScreen.name) {
                    FavoriteScreen(navController)
                }
                composable(route = Screens.AccountScreen.name) {
                    AccountScreen(navController)
                }
                composable(route = Screens.HistoryScreen.name) {
                    HistoryScreen(navController)
                }
            }
        }
    }
}

