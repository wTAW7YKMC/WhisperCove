package com.whispercove.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.whispercove.app.ui.screens.*

@Composable
fun WhisperCoveNavigation() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Explore.route) { ExploreScreen(navController) }
            composable(Screen.Create.route) { CreateScreen(navController) }
            composable(Screen.Connect.route) { ConnectScreen(navController) }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
        }
    }
}

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : Screen("home", "首页", Icons.Default.Home)
    object Explore : Screen("explore", "探索", Icons.Default.Search)
    object Create : Screen("create", "创作", Icons.Default.Add)
    object Connect : Screen("connect", "连接", Icons.Default.People)
    object Profile : Screen("profile", "我的", Icons.Default.Person)
}

val items = listOf(
    Screen.Home,
    Screen.Explore,
    Screen.Create,
    Screen.Connect,
    Screen.Profile
)