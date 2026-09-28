package cl.alexis.p2.exa_p2_alexisr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class ListaMedidoresFragment : Fragment() {

    private val viewModel: MedidorViewModel by viewModels()
    private lateinit var adapter: MedidorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_medidores, container, false)

        // 1. Configurar el RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMedidores)
        adapter = MedidorAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 2. Configurar el botón para navegar
        val btnAgregar = view.findViewById<FloatingActionButton>(R.id.btnIrARegistrar)
        btnAgregar.setOnClickListener {
            findNavController().navigate(R.id.action_listaMedidoresFragment_to_registroMedidorFragment)
        }

        // 3. Observar los datos EN TIEMPO REAL
        // repeatOnLifecycle hace que la lista se refresque apenas vuelvas de la otra pantalla
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listaMedidores.collect { lista ->
                    adapter.actualizarLista(lista)

                    // Si hay datos, nos movemos al último para confirmar que se guardó
                    if (lista.isNotEmpty()) {
                        recyclerView.scrollToPosition(lista.size - 1)
                    }
                }
            }
        }

        return view
    }
}