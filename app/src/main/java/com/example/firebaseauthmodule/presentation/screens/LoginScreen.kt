package com.example.firebaseauthmodule.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "Login Screen",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp)
        )
        Button(
            onClick = { },
            modifier = Modifier
                .padding(14.dp)
        ) {
            Text(text = "Login with Gmail account")
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}