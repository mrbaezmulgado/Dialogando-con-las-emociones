package com.dialogandoemocion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaDialogo(navController: NavController) {

    var textoUsuario by remember { mutableStateOf("") }
    val listaEmociones = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Escribe cómo te sientes:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = textoUsuario,
            onValueChange = { textoUsuario = it },
            label = { Text("Tu emoción aquí...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (textoUsuario.isNotEmpty()) {
                    listaEmociones.add("Tú: $textoUsuario")
                    textoUsuario = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar emoción")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Historial del diálogo:")
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(listaEmociones) { emocion ->
                Text("- $emocion")
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}
