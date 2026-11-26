package enriquez.mariajose.thecheezery.model.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import enriquez.mariajose.thecheezery.model.data.database.entity.ComboEntity
import enriquez.mariajose.thecheezery.model.data.database.relation.ComboWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface ComboDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCombo(combo: ComboEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCombos(combos: List<ComboEntity>)

    @Update
    suspend fun updateCombo(combo: ComboEntity)

    @Delete
    suspend fun deleteCombo(combo: ComboEntity)

    @Query("SELECT * FROM Combo")
    fun getAllCombos(): Flow<List<ComboEntity>>

    @Query("SELECT * FROM Combo WHERE idCombo = :comboId")
    suspend fun getComboById(comboId: Int): ComboEntity?

    @Query("SELECT * FROM Combo WHERE nameCombo LIKE '%' || :name || '%'")
    fun searchCombos(name: String): Flow<List<ComboEntity>>

    @Transaction
    @Query("SELECT * FROM Combo WHERE idCombo = :comboId")
    suspend fun getComboWithProducts(comboId: Int): ComboWithProducts?

    @Transaction
    @Query("SELECT * FROM Combo ORDER BY nameCombo")
    fun getAllCombosWithProducts(): Flow<List<ComboWithProducts>>
}
