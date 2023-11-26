package com.gaitan.dev.mobileboldandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.gaitan.dev.clima_presentacion.pantalla.PantallaBusquedaUbicacion
import com.gaitan.dev.mobileboldandroid.navegacion.GrafoDeNavegacionApp
import com.gaitan.dev.mobileboldandroid.ui.theme.MobileBoldAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import io.sentry.Sentry

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    // waiting for view to draw to better represent a captured error with a screenshot
    findViewById<android.view.View>(android.R.id.content).viewTreeObserver.addOnGlobalLayoutListener {
      try {
        throw Exception("This app uses Sentry! :)")
      } catch (e: Exception) {
        Sentry.captureException(e)
      }
    }

        setContent {
            MobileBoldAndroidTheme {
                val controladoNavegacion = rememberNavController()
                val estadoEstructuraVisual = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = estadoEstructuraVisual
                ) { valorRelleno ->
                    GrafoDeNavegacionApp(
                        modifier = Modifier.padding(valorRelleno),
                        navControlador = controladoNavegacion
                    )
                }
            }
        }
    }
}