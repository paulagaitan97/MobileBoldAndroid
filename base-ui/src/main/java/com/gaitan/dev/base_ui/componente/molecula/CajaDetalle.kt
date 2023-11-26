package com.gaitan.dev.base_ui.componente.molecula

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FechaButton(fecha: String, onFechaSeleccionada: () -> Unit) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .clickable { onFechaSeleccionada() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = fecha, color = Color.White)
    }
}