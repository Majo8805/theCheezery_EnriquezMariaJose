package enriquez.mariajose.thecheezery.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import enriquez.mariajose.thecheezery.R
import enriquez.mariajose.thecheezery.ui.theme.Brighter_Pink
import enriquez.mariajose.thecheezery.ui.theme.Pinky
import enriquez.mariajose.thecheezery.ui.theme.Very_purple

@Composable
fun MenuScreen(innerPadding : PaddingValues, navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        IconButton(
            onClick = {  },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color.White,
                containerColor = Pinky,
            ),
            modifier = Modifier
                .padding(end = 20.dp, top = 20.dp)
                .size(50.dp)
                .align(Alignment.End)
        ) {
            Icon(
                painterResource(R.drawable.cart),
                contentDescription = "Carrito de compras",
                Modifier.size(30.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.grupo2),
            contentDescription = "Imagen de menú",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Brighter_Pink, Very_purple)
                        )
                    ),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hot Drinks",
                    color = Color.White,
                    modifier = Modifier
                        .clickable { navController.navigate("show_products/Hot Drinks") }
                )
                Text(
                    text = "Salties",
                    color = Color.White,
                    modifier = Modifier
                        .clickable { navController.navigate("show_products/Salties") }
                )
                Text(
                    text = "Combos",
                    color = Color.White,
                    modifier = Modifier
                        .clickable { }
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Brighter_Pink, Very_purple)
                        )
                    ),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cold Drinks",
                    color = Color.White,
                    modifier = Modifier
                        .clickable { navController.navigate("show_products/Cold Drinks") }
                )
                Text(
                    text = "Sweets",
                    color = Color.White,
                    modifier = Modifier
                        .clickable { navController.navigate("show_products/Sweets") }
                )
                Text(
                    text = "Add new product",
                    color = Color.White,
                    modifier = Modifier
                        .clickable { }
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun MenuScreenPreview() {
    MenuScreen(PaddingValues(0.dp), rememberNavController())
}