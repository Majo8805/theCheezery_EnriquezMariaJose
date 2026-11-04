package enriquez.mariajose.thecheezery.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import enriquez.mariajose.thecheezery.R
import enriquez.mariajose.thecheezery.ui.theme.Less_Purple
import enriquez.mariajose.thecheezery.ui.theme.Pinky
import enriquez.mariajose.thecheezery.view.components.CarritoElement
import enriquez.mariajose.thecheezery.viewModel.CartViewModel

@Composable
fun CartScreen(innerPadding: PaddingValues, viewModel: CartViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White)
    ) {
        Text(
            text = "My Order",
            color = Pinky,
            fontSize = 60.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.hello_bunny))
        )
        if (viewModel.cartItems.isEmpty()) {
            Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Add some products to your order!",
                    color = Color.Gray,
                    fontSize = 20.sp
                )
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.cartItems) { product ->
                    CarritoElement(
                        product = product,
                        onRemove = { viewModel.removeFromCart(product) }
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 48.dp, top = 16.dp, end = 32.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Total ", color = Color.DarkGray, fontSize = 40.sp)
            Text(text = "$${viewModel.getTotal()}", color = Less_Purple, fontSize = 50.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CartScreenPreview() {
    CartScreen(innerPadding = PaddingValues(0.dp), viewModel = viewModel())
}