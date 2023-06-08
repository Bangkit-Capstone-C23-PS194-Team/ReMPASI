package com.caps.rempasi.presentation.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.common.UIState
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme
import com.caps.rempasi.presentation.ui.theme.White

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    splashViewModel: SplashViewModel = hiltViewModel(),
    firstScreenCallback: (String) -> Unit,
) {
    splashViewModel.startDestination.collectAsState(initial = UIState.Loading).value.let { start ->
        when (start) {
            is UIState.Loading -> splashViewModel.getStartDestination()
            is UIState.Success -> {
                LaunchedEffect(key1 = true) {
                    firstScreenCallback(start.data)
                }
            }
            is UIState.Error -> {
                Box(modifier = modifier.fillMaxWidth()) {
                    Text(
                        text = start.message,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_transparent),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center).size(128.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SplashPrev() {
    ReMPASITheme {
        SplashScreen(firstScreenCallback = {})
    }
}