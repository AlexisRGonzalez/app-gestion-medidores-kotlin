package cl.alexis.p2.exa_p2_alexisr

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MedidorDao {
    @Insert
    suspend fun insertar(medidor: Medidor)

    // agregamos Flow para que sea reactivo
    @Query("SELECT * FROM tabla_medidores ORDER BY id DESC")
    fun obtenerTodos(): Flow<List<Medidor>>
}