package enriquez.mariajose.thecheezery.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import enriquez.mariajose.thecheezery.model.data.coldDrinks
import enriquez.mariajose.thecheezery.model.data.hotDrinks
import enriquez.mariajose.thecheezery.model.data.salties
import enriquez.mariajose.thecheezery.model.data.sweets
import enriquez.mariajose.thecheezery.model.data.database.entity.ComboEntity
import enriquez.mariajose.thecheezery.model.data.database.entity.ProductEntity
import enriquez.mariajose.thecheezery.model.data.database.entity.toEntity
import enriquez.mariajose.thecheezery.model.data.database.AppDatabase
import enriquez.mariajose.thecheezery.model.data.repository.CheezeryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CheezeryViewModel(
    private val repository: CheezeryRepository,
    private val context: Context
) : ViewModel() {
    val products = repository.getAllProducts().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val combos = repository.getAllCombosWithProducts().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        seedProductsIfNeeded()
    }

    fun productsByCategory(category: String): Flow<List<ProductEntity>> {
        return repository.getProductsByCategory(category)
    }

    suspend fun createCombo(name: String, price: Float, productIds: List<Int>) {
        repository.createCombo(
            combo = ComboEntity(name = name.trim(), price = price),
            productIds = productIds
        )
    }

    private fun seedProductsIfNeeded() {
        viewModelScope.launch {
            if (repository.getAllProductsOnce().isEmpty()) {
                val catalog = listOf(
                    "Cold Drinks" to coldDrinks,
                    "Hot Drinks" to hotDrinks,
                    "Salties" to salties,
                    "Sweets" to sweets
                )
                val products: List<ProductEntity> = catalog.flatMap { (category, items) ->
                    items.map { product -> product.toEntity(context, category) }
                }
                repository.insertProducts(products)
            }
        }
    }

    class Factory(context: Context) : ViewModelProvider.Factory {
        private val appContext = context.applicationContext
        private val repository = CheezeryRepository(AppDatabase.getInstance(appContext))

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (!modelClass.isAssignableFrom(CheezeryViewModel::class.java)) {
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
            return modelClass.cast(CheezeryViewModel(repository, appContext))
                ?: throw IllegalStateException("Could not create ${modelClass.name}")
        }
    }
}
