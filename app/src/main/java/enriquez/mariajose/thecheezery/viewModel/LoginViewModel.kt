package enriquez.mariajose.thecheezery.viewModel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var errorMessage by mutableStateOf("")

    fun validateLogin(email: String, passwordText: String, onSuccess: () -> Unit) {
        if (email.isBlank() || passwordText.isBlank()) {
            errorMessage = "Todos los campos son obligatorios"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "El formato del correo es inválido"
        } else {
            errorMessage = ""
            onSuccess()
        }
    }
}