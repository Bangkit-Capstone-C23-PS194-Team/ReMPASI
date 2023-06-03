package com.caps.rempasi.presentation.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.caps.rempasi.presentation.ui.theme.*

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Tersimpan",
                icon = Icons.Default.Favorite,
                screen = Screen.Profile
            ),
            NavigationItem(
                title = "Beranda",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Profil",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        BottomNavigation(
            backgroundColor = Red,
        ) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selectedContentColor = RedSecondary,
                    unselectedContentColor = White,
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    ReMPASITheme() {
        BottomBar(navController = rememberNavController())
    }
}