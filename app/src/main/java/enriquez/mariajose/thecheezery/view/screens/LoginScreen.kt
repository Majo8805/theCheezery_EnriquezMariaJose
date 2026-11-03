package enriquez.mariajose.thecheezery.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import enriquez.mariajose.thecheezery.R
import enriquez.mariajose.thecheezery.ui.theme.Brighter_Pink

@Composable
fun LoginScreen(innerPadding : PaddingValues, onNavigateToMenu: () -> Unit) {

    var user by remember { mutableStateOf("") }
    var password = remember { TextFieldState() }

    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(R.drawable.grupo1),
            contentDescription = "cheezy_logo",
            Modifier.size(200.dp))
        TextField(
            modifier = Modifier.padding(bottom = 10.dp),
            value = user,
            label = { Text("Correo Electronico") },
            onValueChange = { it -> user = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        SecureTextField(
            state = password,
            modifier = Modifier.padding(bottom = 15.dp),
            label = { Text("Contraseña") },
            textObfuscationMode = TextObfuscationMode.RevealLastTyped,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(
            onClick = {onNavigateToMenu()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Brighter_Pink,
                contentColor = Color.White
            )
        ) {
            Text("Entrar")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(PaddingValues(0.dp),{})
}