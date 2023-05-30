package com.caps.rempasi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import com.caps.rempasi.presentation.ui.navigation.NavigationGraph
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReMPASITheme {
                val navController = rememberNavController()
                NavigationGraph(
                    navController = navController,
                )
            }
        }
    }
}