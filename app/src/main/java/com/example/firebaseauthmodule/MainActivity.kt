package com.example.firebaseauthmodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.rememberNavController
import com.example.firebaseauthmodule.presentation.navigation.NavGraph


import com.example.firebaseauthmodule.presentation.ui.theme.FirebaseAuthModuleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirebaseAuthModuleTheme {
                NavGraph(navController = rememberNavController())
            }
        }
    }
}

