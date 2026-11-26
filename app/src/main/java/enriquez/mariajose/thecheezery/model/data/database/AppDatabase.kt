package enriquez.mariajose.thecheezery.model.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import enriquez.mariajose.thecheezery.model.data.database.dao.ComboDAO
import enriquez.mariajose.thecheezery.model.data.database.dao.ProductComboDAO
import enriquez.mariajose.thecheezery.model.data.database.dao.ProductDAO
import enriquez.mariajose.thecheezery.model.data.database.entity.ComboEntity
import enriquez.mariajose.thecheezery.model.data.database.entity.ProductComboEntity
import enriquez.mariajose.thecheezery.model.data.database.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        ComboEntity::class,
        ProductComboEntity::class
    ],
    version = 2,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO
    abstract fun comboDao(): ComboDAO
    abstract fun productComboDao(): ProductComboDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cheezery.db"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}