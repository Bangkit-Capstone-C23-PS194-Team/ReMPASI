package com.caps.rempasi.presentation.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.theme.*

@Composable
fun BottomBar(
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
                icon = painterResource(id = R.drawable.bookmark),
                screen = listOf(Screen.Saved)
            ),
            NavigationItem(
                title = "Kamera",
                icon = painterResource(id = R.drawable.camera_home),
                screen = listOf(Screen.Home, Screen.CameraResult, Screen.RecommendationResult)
            ),
            NavigationItem(
                title = "Profil",
                icon = painterResource(id = R.drawable.account_circle),
                screen = listOf(Screen.Profile)
            ),
        )
        BottomNavigation(
            backgroundColor = RedSecondary,
        ) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(item.title) },
                    selectedContentColor = Red,
                    unselectedContentColor = RedAccent,
                    selected = currentRoute in item.screen.map { it.route },
                    onClick = {
                        navController.navigate(item.screen.first().route) {
                            popUpTo(Screen.Home.route) {
                                if (currentRoute != Screen.CameraResult.route || currentRoute != Screen.RecommendationResult.route) {
                                    saveState = true
                                }
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