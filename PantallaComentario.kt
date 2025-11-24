package com.dialogandoemocion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaComentario(navController: NavController) {
    var comentario by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "¿Qué te gustaría que agregáramos al juego?",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        BasicTextField(
            value = comentario,
            onValueChange = { comentario = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp),
            textStyle = TextStyle.Default
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { 
            // Aquí podrías guardar el comentario o hacer alguna acción
            navController.navigate("dialogo") 
        }) {
            Text("Enviar")
        }
    }
}
