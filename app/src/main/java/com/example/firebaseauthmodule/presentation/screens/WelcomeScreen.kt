package com.example.firebaseauthmodule.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.firebaseauthmodule.presentation.viewmodel.SharedViewModel
import com.example.firebaseauthmodule.presentation.viewmodel.WelcomeViewModel


@Composable
fun WelcomeScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val user = sharedViewModel.user.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        user?.let {
            welcomeViewModel.setUser(it)
            Text(text = "Welcome, ${it.displayName}")
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Email: ${it.email}")
            Spacer(modifier = Modifier.size(16.dp))
            it.photoUrl?.let { photoUrl ->
                Image(
                    painter = rememberImagePainter(data = photoUrl),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(64.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            welcomeViewModel.signOut(
                onSignOut = {
                    sharedViewModel.clearUser()
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onError = { exception ->

                    println("Error during sign out: ${exception.message}")
                }
            )
        }) {
            Text(text = "Se déconnecter")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
        }) {
            Text(text = "Quitter")
        }
    }
}