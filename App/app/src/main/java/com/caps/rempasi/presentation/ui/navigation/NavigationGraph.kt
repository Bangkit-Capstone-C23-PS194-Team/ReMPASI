package com.caps.rempasi.presentation.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caps.rempasi.presentation.ui.screen.SharedCameraResultViewModel
import com.caps.rempasi.presentation.ui.screen.auth.AuthScreen
import com.caps.rempasi.presentation.ui.screen.auth.AuthViewModel
import com.caps.rempasi.presentation.ui.screen.auth.GoogleAuthUiClient
import com.caps.rempasi.presentation.ui.screen.auth.WelcomeScreen
import com.caps.rempasi.presentation.ui.screen.camera.CameraResultScreen
import com.caps.rempasi.presentation.ui.screen.home.HomeScreen
import com.caps.rempasi.presentation.ui.screen.onboarding.OnBoardingScreen
import com.caps.rempasi.presentation.ui.screen.profile.ProfileScreen
import com.caps.rempasi.presentation.ui.screen.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route,
    googleAuthUiClient: GoogleAuthUiClient,
    appContext: Context,
    lifeCycleScope: LifecycleCoroutineScope
) {

    val sharedViewModel: SharedCameraResultViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen {
                navController.popBackStack()
                navController.navigate(it)
            }
        }
        composable(route = Screen.Onboarding.route) {
            OnBoardingScreen {
                navController.popBackStack()
                navController.navigate(Screen.Auth.route)
            }
        }
        composable(route = Screen.Home.route) {
            HomeScreen(
                sharedViewModel = sharedViewModel,
                navigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            ) {
                navController.navigate(Screen.CameraResult.route)
            }
        }
        composable(route = Screen.Auth.route) {
            val viewModel = viewModel<AuthViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() != null) {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        lifeCycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        appContext,
                        "Login Berhasil",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.popBackStack()
                    navController.navigate(Screen.Welcome.route)
                    viewModel.resetState()
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AuthScreen(
                    state = state,
                    onSignInClick = {
                        lifeCycleScope.launch {
                            viewModel.setLoading(true)
                            val signInIntentSender = googleAuthUiClient.signIn()
                            viewModel.setLoading(false)
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }
                    }
                )
                if (state.isLoading) {
                    Dialog(
                        onDismissRequest = { },
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
        composable(route = Screen.Welcome.route) {
            googleAuthUiClient.getSignedInUser()?.username?.let { username ->
                WelcomeScreen(name = username) {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            }
        }
        composable(
            route = Screen.CameraResult.route,
        ) {
            CameraResultScreen(
                sharedViewModel = sharedViewModel,
                navigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            ) {
                navController.popBackStack()
            }
        }
        composable(Screen.Profile.route) {
            val userData = googleAuthUiClient.getSignedInUser()
            userData?.let {
                ProfileScreen(
                    accountName = it.username ?: "",
                    email = it.email ?: "",
                    profile = it.profilePictureUrl ?: "",
                    navigateBack = { navController.popBackStack() },
                    logOut = {
                        lifeCycleScope.launch {
                            googleAuthUiClient.signOut()
                            Toast.makeText(
                                appContext,
                                "Logout Berhasil",
                                Toast.LENGTH_LONG
                            ).show()

                            navController.navigate(Screen.Auth.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}