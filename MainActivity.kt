package com.dialogandoemocion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialogandoEmocionApp()
        }
    }
}
package com.dialogandoemocion

import androidx.compose.runtime.Composable
import com.dialogandoemocion.navigation.AppNavigation

@Composable
fun DialogandoEmocionApp() {
    AppNavigation()
}
