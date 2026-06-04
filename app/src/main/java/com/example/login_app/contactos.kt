package com.example.login_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login_app.ui.theme.Login_appTheme

data class Contacto(
    val id: Int,
    val nombre: String,
    val telefono: String,
    val bgColor: Color,
    val iconColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactosScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    val contactos = remember {
        mutableStateListOf(
            Contacto(1, "Ana García", "+34 612 345 678", Color(0xFFE3F2FD), Color(0xFF1E88E5)),
            Contacto(2, "Carlos Rodríguez", "+34 699 888 777", Color(0xFFE8F5E9), Color(0xFF43A047)),
            Contacto(3, "Elena Martínez", "+34 655 443 322", Color(0xFFFFF3E0), Color(0xFFFB8C00))
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mis Contactos", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* Abrir menú */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Perfil */ }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Perfil")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFAFAFA)
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Contactos") },
                    label = { Text("Contactos") },
                    selected = true,
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF3F51B5),
                        selectedTextColor = Color(0xFF3F51B5),
                        indicatorColor = Color(0xFFE8EAF6)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Recientes") },
                    label = { Text("Recientes") },
                    selected = false,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, contentDescription = "Favoritos") },
                    label = { Text("Favoritos") },
                    selected = false,
                    onClick = { }
                )
            }
        },
        containerColor = Color(0xFFFAFAFA)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // nombre
            Text("Nombre", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                placeholder = { Text("Ej: Juan Pérez", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            // teléfono
            Text("Teléfono", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                placeholder = { Text("+34 600 000 000", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color.Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botón para agregar contacto
            Button(
                onClick = {
                    if (nombre.isNotBlank() && telefono.isNotBlank()) {
                        val newId = (contactos.maxOfOrNull { it.id } ?: 0) + 1
                        contactos.add(
                            Contacto(
                                id = newId,
                                nombre = nombre,
                                telefono = telefono,
                                bgColor = Color(0xFFF3E5F5),
                                iconColor = Color(0xFF8E24AA)
                            )
                        )
                        nombre = ""
                        telefono = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
                enabled = nombre.isNotBlank() && telefono.isNotBlank()
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Agregar Contacto", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            // Botón para limpiar los campos
            TextButton(
                onClick = {
                    nombre = ""
                    telefono = ""
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(Icons.Default.Delete, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Limpiar Campos", color = Color.Gray, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Título de la lista
            Text(
                text = "LISTA DE CONTACTOS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(contactos) { contacto ->
                    ContactoItem(contacto)
                }
            }
        }
    }
}

@Composable
fun ContactoItem(contacto: Contacto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(contacto.bgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = contacto.iconColor)
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Info del contacto
        Column(modifier = Modifier.weight(1f)) {
            Text(text = contacto.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Phone, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = contacto.telefono, color = Color.Gray, fontSize = 14.sp)
            }
        }

        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.LightGray)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContactos() {
    Login_appTheme {
        ContactosScreen(rememberNavController())
    }
}