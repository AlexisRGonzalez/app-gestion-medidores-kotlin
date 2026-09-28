package cl.alexis.p2.exa_p2_alexisr

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Configuración de la base de datos SQLite con ROOM
@Database(entities = [Medidor::class], version = 1)
abstract class MedidorDatabase : RoomDatabase() {

    abstract fun medidorDao(): MedidorDao

    companion object {
        @Volatile
        private var INSTANCE: MedidorDatabase? = null

        // getDatabase metodo que asegura que toda la aplicacion use una sola conexion a la base de datos para evitar que se vuelva lenta
        fun getDatabase(context: Context): MedidorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedidorDatabase::class.java,
                    "medidores_db" // El nombre interno del archivo en el celular
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}