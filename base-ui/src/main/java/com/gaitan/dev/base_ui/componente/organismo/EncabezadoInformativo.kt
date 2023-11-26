package com.gaitan.dev.base_ui.componente.organismo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EncabezadoInformativo(
    modifier: Modifier,
    textoCiudad: String,
    textoRegion: String,
    textoPais: String,
    valorTemperatura: Double
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = textoCiudad,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = textoRegion,
                style = MaterialTheme.typography.h5,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = textoPais,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$valorTemperatura °C",
                style = MaterialTheme.typography.h3,
                fontSize = 28.sp
            )
        }
    }
}
