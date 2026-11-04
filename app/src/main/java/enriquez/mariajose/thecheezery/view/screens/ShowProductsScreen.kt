package enriquez.mariajose.thecheezery.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import enriquez.mariajose.thecheezery.R
import enriquez.mariajose.thecheezery.data.getProductsByCategory
import enriquez.mariajose.thecheezery.domain.Product
import enriquez.mariajose.thecheezery.ui.theme.Pinky
import enriquez.mariajose.thecheezery.view.components.FoodComponent

@Composable
fun ShowProductsScreen(
    category: String,
    innerPadding: PaddingValues
) {
    val productList = getProductsByCategory(category)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White)
    ) {
        Text(
            text = category,
            color = Pinky,
            fontSize = 88.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.hello_bunny))
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(productList) { product ->
                FoodComponent(product)
            }
        }
    }
}

@Composable
@Preview
fun ShowProductsScreenPreview() {
    ShowProductsScreen("Cold Drinks", PaddingValues(0.dp)
    )
}