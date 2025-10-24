// PrimaryButton.kt

package mx.edu.utez.demo.ui.components.buttons // Ajusta tu paquete si es necesario

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled, // ¡Aquí se usa el parámetro 'enabled'!
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp), // Estilo del diseño
        colors = ButtonDefaults.buttonColors(

            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {

        Text(text)
    }
}