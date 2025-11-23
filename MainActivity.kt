package com.dialogandoemocion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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

        // 1️⃣ Pantalla de Inicio
        composable("inicio") {
            PantallaInicio(navController)
        }

        // 2️⃣ Pantalla donde el jugador escribe sus emociones
        composable("dialogo") {
            PantallaDialogo(
                navController = navController,
                emocionRepetidaCount = emocionRepetidaCount,
                ultimaEmocion = ultimaEmocion
            )
        }

        // 3️⃣ Pantalla con información de la emoción detectada
        composable("info_emocion") {
            PantallaInfoEmocion(
                navController = navController,
                emocion = ultimaEmocion.value
            )
        }

        // 4️⃣ Pantalla de comentario del jugador
        composable("comentario") {
            PantallaComentario(navController)
        }

        // 5️⃣ Pantalla de dibujo/actividad emocional
        composable("dibujo") {
            PantallaDibujo(navController)
        }
    }
}

//////////////////////////////////////////////////////////////////////////////
// 1️⃣ PANTALLA INICIO
//////////////////////////////////////////////////////////////////////////////

@Composable
fun PantallaInicio(navController: NavHostController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("🌈 Dialogando con la Emoción", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(30.dp))
            Text("Un espacio seguro para expresar cómo te sientes.")

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { navController.navigate("dialogo") }) {
                Text("Empezar")
            }
        }
    }
}

//////////////////////////////////////////////////////////////////////////////
// 2️⃣ PANTALLA DIÁLOGO PRINCIPAL
//////////////////////////////////////////////////////////////////////////////

@Composable
fun PantallaDialogo(
    navController: NavHostController,
    emocionRepetidaCount: MutableState<Int>,
    ultimaEmocion: MutableState<String>
) {
    var texto by remember { mutableStateOf("") }
    var respuesta by remember { mutableStateOf("") }

    fun detectarEmocion(texto: String): String {
        val emociones = listOf("triste", "ansioso", "feliz", "molesto", "solo", "cansado")
        return emociones.firstOrNull { texto.contains(it, ignoreCase = true) } ?: "emocion_desconocida"
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Text("Escribe cómo te sientes hoy:")

            Spacer(modifier = Modifier.height(20.dp))

            BasicTextField(
                value = texto,
                onValueChange = { texto = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground)
            )Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                val emocion = detectarEmocion(texto)
                ultimaEmocion.value = emocion

                respuesta = when (emocion) {
                    "triste" -> "Siento que estés triste. Estoy contigo."
                    "ansioso" -> "La ansiedad pesa, pero no estás solo."
                    "molesto" -> "Es válido enojarse. Hablemos de ello."
                    "feliz" -> "¡Qué alegría sentirte así!"
                    else -> "Gracias por compartir lo que sientes."
                }

                if (ultimaEmocion.value == emocion && emocion != "emocion_desconocida") {
                    emocionRepetidaCount.value++
                } else {
                    emocionRepetidaCount.value = 1
                }

                if (emocionRepetidaCount.value >= 3) {
                    navController.navigate("dibujo")
                }
            }) {
                Text("Enviar emoción")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(respuesta)
            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { navController.navigate("info_emocion") }) {
                Text("Ver más sobre mi emoción")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navController.navigate("comentario") }) {
                Text("Dejar comentario del juego")
            }
        }
    }
}

@Composable
fun PantallaInfoEmocion(navController: NavHostController, emocion: String) {
    val descripcion = when (emocion) {
        "triste" -> "La tristeza aparece cuando algo nos duele o perdemos algo importante."
        "ansioso" -> "La ansiedad te prepara para un riesgo, aunque a veces es exagerada."
        "feliz" -> "La felicidad te da energía y motivación."
        "molesto" -> "El enojo señala que algo te parece injusto."
        else -> "Cada emoción tiene un mensaje importante."
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
        ) {
            Text("Tu emoción: $emocion", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(20.dp))
            Text(descripcion)
            Spacer(Modifier.height(40.dp))
            Button(onClick = { navController.navigate("dialogo") }) {
                Text("Volver")
            }
        }
    }
}

@Composable
fun PantallaComentario(navController: NavHostController) {
    var comentario by remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
        ) {
            Text("¿Qué te gustaría que agregáramos al juego?")
            Spacer(Modifier.height(20.dp))
            BasicTextField(
                value = comentario,
                onValueChange = { comentario = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp),
                textStyle = TextStyle.Default
            )
            Spacer(Modifier.height(30.dp))
            Button(onClick = { navController.navigate("dialogo") }) {
                Text("Enviar")
            }
        }
    }
}

@Composable
fun PantallaDibujo(navController: NavHostController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Actividad desbloqueada 🎨")
            Spacer(Modifier.height(20.dp))
            Text("Haz un dibujo de ti mismo y describe cómo te sientes.")
            Spacer(Modifier.height(40.dp))
            Button(onClick = { navController.navigate("dialogo") }) {
                Text("Volver")
            }
        }
    }
}
