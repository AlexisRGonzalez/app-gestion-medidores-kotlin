package cl.alexis.p2.exa_p2_alexisr

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =
"tabla_medidores")
data class Medidor(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tipo: String, //Almacenará "Agua", "Luz" o "Gas"
    val valor: Int, //valor numerico
    val fecha: String // fecha del registro
)