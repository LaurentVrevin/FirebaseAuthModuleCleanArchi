package com.example.firebaseauthmodule.presentation.screens


import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firebaseauthmodule.R
import com.example.firebaseauthmodule.presentation.viewmodel.LoginViewModel
import com.example.firebaseauthmodule.presentation.viewmodel.SharedViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


@Composable
fun LoginScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val user = sharedViewModel.user.collectAsState().value
    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            loginViewModel.signInWithGoogle(account.idToken!!,
                onSignInSuccess = { user ->
                    sharedViewModel.setUser(user)
                    navController.navigate("welcome")
                },
                onSignInError = { e ->
                    // Afficher un message d'erreur à l'utilisateur
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                })
        } catch (e: ApiException) {
            // Handle exception
        }
    }

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (user == null) {
                "Merci de vous connecter"
            } else {
                "Vous êtes connecté en tant que ${user.email}"
            }
        )
        Button(onClick = {
            val signInIntent = googleSignInClient.signInIntent
            signInLauncher.launch(signInIntent)
        }) {
            Text(text = "Sign in with Google")
        }
    }
}