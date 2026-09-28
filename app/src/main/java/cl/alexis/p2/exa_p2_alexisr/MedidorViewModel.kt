package cl.alexis.p2.exa_p2_alexisr

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MedidorViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = MedidorDatabase.getDatabase(application).medidorDao()

    // Se transforma el Flow del DAO en un StateFlow para la UI
    val listaMedidores: StateFlow<List<Medidor>> = dao.obtenerTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertarMedidor(medidor: Medidor) {
        viewModelScope.launch {
            dao.insertar(medidor)

        }
    }
}