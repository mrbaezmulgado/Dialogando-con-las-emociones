@Composable
fun PantallaDialogo(
    navController: NavController,
    onEmocionDetectada: (String) -> Unit = {}
) {
    var textoUsuario by remember { mutableStateOf("") }
    val listaEmociones = remember { mutableStateListOf<String>() }

    fun detectarEmocion(texto: String): String {
        val emociones = listOf("triste", "ansioso", "feliz", "molesto", "solo", "cansado")
        return emociones.firstOrNull { texto.contains(it, ignoreCase = true) } ?: "emocion_desconocida"
    }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {
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
                    val emocionDetectada = detectarEmocion(textoUsuario)
                    listaEmociones.add("Tú: $textoUsuario")
                    listaEmociones.add("Sistema: $emocionDetectada")
                    onEmocionDetectada(emocionDetectada)
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

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(listaEmociones) { emocion ->
                if (emocion.startsWith("Tú:")) {
                    // Mensaje del usuario
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Text(
                            text = emocion,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                } else {
                    // Mensaje del sistema
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(
                            text = emocion,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
