package enriquez.mariajose.thecheezery.model.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import enriquez.mariajose.thecheezery.model.data.database.entity.ProductComboEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductComboDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCombo(productCombo: ProductComboEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCombos(productCombos: List<ProductComboEntity>)

    @Update
    suspend fun updateProductCombo(productCombo: ProductComboEntity)

    @Delete
    suspend fun deleteProductCombo(productCombo: ProductComboEntity)

    @Query("SELECT * FROM ProductsCombo")
    fun getAllProductCombos(): Flow<List<ProductComboEntity>>

    @Query("SELECT * FROM ProductsCombo WHERE idCombo = :comboId")
    fun getProductCombosByComboId(comboId: Int): Flow<List<ProductComboEntity>>

    @Query("SELECT * FROM ProductsCombo WHERE idProduct = :productId")
    fun getProductCombosByProductId(productId: Int): Flow<List<ProductComboEntity>>
}
