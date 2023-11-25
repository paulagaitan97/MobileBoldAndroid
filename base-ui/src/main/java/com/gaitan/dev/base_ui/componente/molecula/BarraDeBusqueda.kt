package com.gaitan.dev.base_ui.componente.molecula

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.gaitan.dev.base_ui.componente.atomo.IconoBase

@Composable
fun BarraDeBusqueda(
    modifier: Modifier = Modifier,
    texto: String,
    textoActual: (String) -> Unit,
    eventoBusqueda: () -> Unit
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = texto,
            onValueChange = textoActual,
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colors.secondary)
                .clip(MaterialTheme.shapes.medium)
                .padding(16.dp),
            keyboardActions = KeyboardActions(
                onSearch = {
                    eventoBusqueda()
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconoBase(
                        icono = Icons.Default.Search,
                        color = MaterialTheme.colors.onSecondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                    }
                }
            }
        )
    }
}
