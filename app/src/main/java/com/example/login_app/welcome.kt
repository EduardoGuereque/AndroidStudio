package com.example.login_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun WelcomeScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5E4AE3)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Welcome",
                color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.width(200.dp)
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { navController.navigate("signup") },
                modifier = Modifier.width(200.dp)
            ) {
                Text("Registrarse")
            }

        }
    }
}