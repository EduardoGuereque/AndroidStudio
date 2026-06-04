package com.example.login_app

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login_app.ui.theme.Login_appTheme

@Composable
fun SingUpScreen(navController: NavController, modifier: Modifier = Modifier) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }

    val isNameValid = nombre.isNotBlank() && nombre.all { it.isLetter() || it.isWhitespace() }
    val isEmailValid = correo.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    val isPhoneValid = numero.length == 10 && numero.all { it.isDigit() }
    val isPasswordValid = contrasena.isNotBlank() && contrasena == confirmarContrasena

    val isFormValid = isNameValid && isEmailValid && isPhoneValid && isPasswordValid

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF5E4AE3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(R.drawable.sing_img),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "REGISTRO",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { newValue ->
                    if (newValue.all { it.isLetter() || it.isWhitespace() }) {
                        nombre = newValue
                    }
                },
                label = { Text("Nombre") },
                isError = nombre.isNotEmpty() && !isNameValid,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            // correo
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                isError = correo.isNotEmpty() && !isEmailValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            // contraseña
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            // confirmar contraseña
            OutlinedTextField(
                value = confirmarContrasena,
                onValueChange = { confirmarContrasena = it },
                label = { Text("Confirmar Contraseña") },
                isError = confirmarContrasena.isNotEmpty() && contrasena != confirmarContrasena,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            // numero
            OutlinedTextField(
                value = numero,
                onValueChange = { newValue ->
                    if (newValue.length <= 10 && newValue.all { it.isDigit() }) {
                        numero = newValue
                    }
                },
                label = { Text("Número (10 dígitos)") },
                isError = numero.isNotEmpty() && !isPhoneValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Botón singup
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(25.dp)),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate("contactos") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    },
                    enabled = isFormValid,
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5E4AE3),
                        disabledContainerColor = Color.LightGray,
                        contentColor = Color.White,
                        disabledContentColor = Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSingup() {
    val navController = rememberNavController()
    Login_appTheme {
        SingUpScreen(navController = navController)
    }
}