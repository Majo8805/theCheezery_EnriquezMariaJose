package enriquez.mariajose.thecheezery.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import enriquez.mariajose.thecheezery.R

@Composable
fun LoginScreen() {
    Column() {
        Image(painter = painterResource(R.drawable.grupo1),
            contentDescription = "cheezy_logo")
        TextField(
            state = rememberTextFieldState(),
            label = { Text("Correo Electronico") }
        )
        TextField(
            state = rememberTextFieldState(),
            label = { Text("Contraseña") }
        )
        Button(
            onClick = {},
            modifier = Modifier
        ) {
            Text("Entrar")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen()
}