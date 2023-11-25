package com.gaitan.dev.base_ui.componente.molecula

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TarjetaBusquedaDetalle(
    titulo: String,
    descripcion: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 1.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = titulo,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = descripcion,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}