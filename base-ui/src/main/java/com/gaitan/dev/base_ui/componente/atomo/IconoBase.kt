package com.gaitan.dev.base_ui.componente.atomo

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconoBase(icono: ImageVector, color: Color){
    Icon(
        imageVector = icono,
        contentDescription = null,
        tint = color
    )
}