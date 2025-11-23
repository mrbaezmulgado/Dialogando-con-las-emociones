package com.dialogandoemocion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Mision(
    val titulo: String,
    val descripcion: String,
    var completada: Boolean = false
)

@Composable
fun PantallaMisiones(navController: NavController) {

    var puntos by remember { mutableStateOf(0) }

    val listaMisiones = remember {
        mutableStateListOf(
            Mision("Respiración profunda", "Realiza 5 respiraciones lentas"),
            Mision("Orden rápido", "Dobla una prenda de ropa"),
            Mision("Movimiento suave", "Estírate durante 20 segundos"),
            Mision("Pausa consciente", "Toma un vaso de agua lentamente"),
            Mision("Pensamiento amable", "Dite una frase de apoyo")
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Misiones de Bienestar",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Puntos: $puntos")

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(listaMisiones) { mision ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    colors = CardDefaults.cardColors()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = mision.titulo, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = mision.descripcion)

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                                if (!mision.completada) {
                                    mision.completada = true
                                    puntos += 10
                                }
                            },
                            enabled = !mision.completada,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                if (mision.completada) "Completada ✓"
                                else "Completar misión"
                            )
                        }
                    }
                }
            }
        }
    }
}
