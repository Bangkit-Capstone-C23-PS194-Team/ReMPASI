package com.caps.rempasi.presentation.ui.screen.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.components.ButtonEndIcon
import com.caps.rempasi.presentation.ui.components.OnboardingContent
import com.caps.rempasi.presentation.ui.navigation.Screen
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.RedSecondary
import com.caps.rempasi.utils.OnBoardingPageData
import com.google.accompanist.pager.*

@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPageData.First,
        OnBoardingPageData.Second,
        OnBoardingPageData.Third,
        OnBoardingPageData.Fourth,
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp)
    ) {
        HorizontalPager(
            modifier = Modifier.weight(7f),
            count = 4,
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically
        ) { position ->
            OnboardingContent(onBoardingPage = pages[position])
        }
        ButtonEndIcon(
            textTitle = stringResource(R.string.learn_more),
            modifier = Modifier.weight(2f),
            pagerState = pagerState
        ) {
            onboardingViewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()
            navController.navigate(Screen.Auth.route)
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            activeColor = Red,
            inactiveColor = RedSecondary,
            spacing = 34.dp,
            indicatorHeight = 20.dp,
            indicatorWidth = 20.dp,
            pagerState = pagerState
        )
    }
}