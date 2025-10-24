package mx.edu.utez.demo.ui.components.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun BodyText(text: String) {
    Text(text = text, fontSize = 18.sp)
}

@Preview
@Composable
fun PreviewBodyText() {
    BodyText("Por favor ingrese el código enviado al correo")
}
