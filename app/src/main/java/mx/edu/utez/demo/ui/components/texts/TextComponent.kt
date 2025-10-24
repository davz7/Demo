package mx.edu.utez.demo.ui.components.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TextComponent(
    text: String,
    style: TextStyle = TextStyle(fontSize = 18.sp)
) {
    Text(text = text, style = style)
}
