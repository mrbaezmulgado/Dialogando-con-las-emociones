package com.dialogandoemocion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialogandoEmocionApp()
        }
    }
}

@Composable
fun DialogandoEmocionApp() {
    val navController = rememberNavController()

    // Memoria: cuántas veces se repite una emoción
    val emocionRepetidaCount = remember { mutableStateOf(0) }
    val ultimaEmocion = remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "inicio") {

        composable("inicio") { PantallaInicio(navController) }

        composable("dialogo") {
            PantallaDialogo(
                navController = navController,
                emocionRepetidaCount = emocionRepetidaCount,
                ultimaEmocion = ultimaEmocion
            )
        }

        composable("info_emocion") {
            PantallaInfoEmocion(navController, ultimaEmocion.value)
        }

        composable("comentario") { PantallaComentario(navController) }

        composable("dibujo") { PantallaDibujo(navController) }
    }
}
