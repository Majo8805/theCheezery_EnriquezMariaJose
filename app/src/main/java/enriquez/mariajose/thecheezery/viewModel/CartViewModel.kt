package enriquez.mariajose.thecheezery.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import enriquez.mariajose.thecheezery.domain.Product

class CartViewModel : ViewModel() {
    val cartItems = mutableStateListOf<Product>()

    fun addToCart(product: Product) {
        cartItems.add(product)
    }

    fun removeFromCart(product: Product) {
        cartItems.remove(product)
    }

    fun getTotal(): Float {
        return cartItems.sumOf { it.price.toDouble() }.toFloat()
    }
}