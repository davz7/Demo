package mx.edu.utez.demo.ui.components.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CodeInputBoxes(
    code: String,
    onCodeChange: (String) -> Unit,
    boxCount: Int = 4 // Por defecto, 4 cajas como en el diseño
) {
    // 1. Array de caracteres para mapear cada caja
    val codeArray = code.padEnd(boxCount, ' ').toCharArray()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 2. Campo de texto oculto para capturar la entrada completa
        BasicTextField(
            value = code,
            onValueChange = { newCode ->
                // Limitar la longitud y permitir solo dígitos
                if (newCode.length <= boxCount && newCode.all { it.isDigit() }) {
                    onCodeChange(newCode)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            // Esto asegura que el cursor del BasicTextField se posicione correctamente
            // aunque esté visualmente oculto por las cajas
            singleLine = true,
            decorationBox = { innerTextField ->
                // 3. Dibujar las cajas visibles encima del BasicTextField
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    codeArray.forEachIndexed { index, char ->
                        CodeBox(
                            char = char,
                            // Resaltar la caja actual para indicar dónde está el foco (simulado)
                            isFocused = index == code.length
                        )
                    }
                }
                // Importante: Llama al innerTextField para que el campo de texto real funcione
                // Aunque no lo veamos, es necesario para que acepte la entrada
                Box(modifier = Modifier.size(0.dp)) {
                    innerTextField()
                }
            }
        )
    }
}

// Sub-componente para dibujar una sola caja
@Composable
private fun CodeBox(char: Char, isFocused: Boolean) {
    Box(
        modifier = Modifier
            .size(56.dp) // Tamaño fijo para la caja
            .border(
                width = 2.dp,

                color = if (isFocused) MaterialTheme.colorScheme.primary else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (char.isDigit()) char.toString() else "",

            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
    }
}