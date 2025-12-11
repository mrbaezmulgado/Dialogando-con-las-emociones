package com.dialogandoemocion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaInfoEmocion(navController: NavController, emocion: String) {

    val descripcion = when (emocion.lowercase()) {
        "triste" -> "La tristeza aparece cuando algo nos duele o perdemos algo importante."
        "ansioso" -> "La ansiedad te prepara para un riesgo, aunque a veces es exagerada."
        "feliz" -> "La felicidad te da energía y motivación."
        "molesto" -> "El enojo señala que algo te parece injusto."
        "solo" -> "Sentirse solo es una señal de que necesitas conexión con otros."
        "cansado" -> "El cansancio indica que tu cuerpo o mente necesitan descanso."
        "ansioso"-> "se refiere a un sintoma de tu cuerpo producido por algo inesperado."
        else -> "Cada emoción tiene un mensaje importante. ¡Escúchate!"
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Tu emoción: $emocion",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = descripcion,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { navController.navigate("dialogo") }) {
            Text("Volver")
        }
    }
