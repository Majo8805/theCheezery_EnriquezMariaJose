package enriquez.mariajose.thecheezery.model.data.repository

import androidx.room.withTransaction
import enriquez.mariajose.thecheezery.model.data.database.AppDatabase
import enriquez.mariajose.thecheezery.model.data.database.entity.ComboEntity
import enriquez.mariajose.thecheezery.model.data.database.entity.ProductComboEntity
import enriquez.mariajose.thecheezery.model.data.database.entity.ProductEntity
import enriquez.mariajose.thecheezery.model.data.database.relation.ComboWithProducts
import kotlinx.coroutines.flow.Flow

class CheezeryRepository(private val database: AppDatabase) {
    private val productDAO = database.productDao()
    private val comboDAO = database.comboDao()
    private val productComboDAO = database.productComboDao()

    suspend fun insertProduct(product: ProductEntity): Long {
        return productDAO.insertProduct(product)
    }

    suspend fun insertProducts(products: List<ProductEntity>) {
        productDAO.insertProducts(products)
    }

    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDAO.getAllProducts()
    }

    fun getProductsByCategory(category: String): Flow<List<ProductEntity>> {
        return productDAO.getProductsByCategory(category)
    }

    suspend fun getProductById(id: Int): ProductEntity? {
        return productDAO.getProductById(id)
    }

    fun getAllCombosWithProducts(): Flow<List<ComboWithProducts>> {
        return comboDAO.getAllCombosWithProducts()
    }

    suspend fun createCombo(combo: ComboEntity, productIds: List<Int>) {
        require(productIds.isNotEmpty()) { "Selecciona al menos un producto para el combo." }

        database.withTransaction {
            val comboId = comboDAO.insertCombo(combo).toInt()
            productComboDAO.insertProductCombos(
                productIds.distinct().map { productId ->
                    ProductComboEntity(idProduct = productId, idCombo = comboId)
                }
            )
        }
    }

    suspend fun getAllProductsOnce(): List<ProductEntity> {
        return productDAO.getAllProductsOnce()
    }
}