package enriquez.mariajose.thecheezery.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import enriquez.mariajose.thecheezery.R
import enriquez.mariajose.thecheezery.ui.theme.Brighter_Pink
import enriquez.mariajose.thecheezery.ui.theme.Purple_grey

@Composable
fun WelcomeScreen(innerPadding : PaddingValues, onNavigateToLogin: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Purple_grey)
            .padding(innerPadding)
    ) {
        Image(
            painter = painterResource(id = R.drawable.the_cheezery),
            contentDescription = "Fondo de bienvenida",
            modifier = Modifier
                .fillMaxWidth()
                .weight(7f),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome to The Cheezery",
                modifier = Modifier
                    .padding(bottom = 8.dp),
                color = Color.White,
                fontSize = 24.sp
            )
            Text(
                text = "Home of the most wonderful desserts ever seen (and tasted) by the human being",
                color = Color.White,
                fontStyle = FontStyle.Italic
            )
            Button(
                onClick = {onNavigateToLogin()},
                modifier = Modifier
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Brighter_Pink,
                    contentColor = Color.White
                ),
            ) {
                Text(text = "Get Started")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WelcomeScreenPreview() {
    WelcomeScreen(PaddingValues(0.dp)) {}
}