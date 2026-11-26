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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import enriquez.mariajose.thecheezery.R
import enriquez.mariajose.thecheezery.model.data.database.entity.toProduct
import enriquez.mariajose.thecheezery.ui.theme.Pinky
import enriquez.mariajose.thecheezery.view.components.FoodElement
import enriquez.mariajose.thecheezery.viewModel.CheezeryViewModel
import enriquez.mariajose.thecheezery.viewModel.CartViewModel

@Composable
fun ShowProductsScreen(
    category: String,
    viewModel: CartViewModel,
    innerPadding: PaddingValues,
    cheezeryViewModel: CheezeryViewModel
) {
    val context = LocalContext.current
    val productsFlow = remember(category, cheezeryViewModel) {
        cheezeryViewModel.productsByCategory(category)
    }
    val productEntities by productsFlow.collectAsState(initial = emptyList())
    val productList = remember(productEntities, context) {
        productEntities.map { it.toProduct(context) }
    }

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
                FoodElement(
                    product = product,
                    onAddToCart = { viewModel.addToCart(product) }
                )
            }
        }
    }
}
