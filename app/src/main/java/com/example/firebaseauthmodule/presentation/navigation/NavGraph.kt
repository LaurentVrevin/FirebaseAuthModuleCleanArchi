package com.example.firebaseauthmodule.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebaseauthmodule.presentation.screens.LoginScreen
import com.example.firebaseauthmodule.presentation.screens.WelcomeScreen
import com.example.firebaseauthmodule.presentation.viewmodel.LoginViewModel
import com.example.firebaseauthmodule.presentation.viewmodel.SharedViewModel
import com.example.firebaseauthmodule.presentation.viewmodel.WelcomeViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = hiltViewModel()
    NavHost(navController, startDestination = "login") {
        composable("login") {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(navController, sharedViewModel, loginViewModel)
        }
        composable("welcome") {
            val welcomeViewModel: WelcomeViewModel = hiltViewModel()
            WelcomeScreen(navController, sharedViewModel, welcomeViewModel)
        }
    }
}