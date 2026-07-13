package com.app.superapp.ui.dashboard

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
import com.app.superapp.core.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onLogout: () -> Unit,
    onNavigateToService: (String) -> Unit
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val items = listOf(
        BottomNavItem("Home", Routes.Home.route, Icons.Default.Home),
        BottomNavItem("Banking", Routes.Banking.route, Icons.Default.AccountBalance),
        BottomNavItem("Payments", Routes.Payments.route, Icons.Default.Payment),
        BottomNavItem("Services", Routes.Services.route, Icons.Default.GridView),
        BottomNavItem("Profile", Routes.Profile.route, Icons.Default.Person)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                Divider()
                NavigationDrawerItem(
                    label = { Text("Logout") },
                    selected = false,
                    onClick = onLogout,
                    icon = { Icon(Icons.Default.Logout, null) }
                )
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.name) },
                            label = { Text(item.name) },
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                navController.navigate(item.route) {
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
                startDestination = Routes.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Routes.Home.route) { 
                    HomeScreen(onServiceClick = onNavigateToService) 
                }
                composable(Routes.Banking.route) { Text("Banking Dashboard") }
                composable(Routes.Payments.route) { Text("Payments Dashboard") }
                composable(Routes.Services.route) { 
                    ServicesScreen(onServiceClick = onNavigateToService) 
                }
                composable(Routes.Profile.route) { Text("User Profile") }
            }
        }
    }
}

@Composable
fun DrawerHeader() {
    // Drawer header implementation
}

data class BottomNavItem(val name: String, val route: String, val icon: ImageVector)
