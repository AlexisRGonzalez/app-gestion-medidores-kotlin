package cl.alexis.p2.exa_p2_alexisr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

class RegistroMedidorFragment : Fragment() {

    // Instanciamos el ViewModel que ya configuraste
    private val viewModel: MedidorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registro_medidor, container, false)

        // Referencias a los componentes del XML
        val rgTipo = view.findViewById<RadioGroup>(R.id.rgTipo)
        val etValor = view.findViewById<EditText>(R.id.etValor)
        val etFecha = view.findViewById<EditText>(R.id.etFecha)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val valorString = etValor.text.toString()
            val fecha = etFecha.text.toString()

            // Obtenemos qué RadioButton se marcó
            val tipoId = rgTipo.checkedRadioButtonId
            val tipo = when (tipoId) {
                R.id.rbAgua -> "Agua"
                R.id.rbLuz -> "Luz"
                R.id.rbGas -> "Gas"
                else -> ""
            }

            // Validación simple
            if (tipo.isNotEmpty() && valorString.isNotEmpty() && fecha.isNotEmpty()) {
                val valor = valorString.toInt()

                // Creamos el objeto Medidor (Punto 5)
                val nuevoMedidor = Medidor(tipo = tipo, valor = valor, fecha = fecha)

                // Guardamos en la BD usando el ViewModel
                viewModel.insertarMedidor(nuevoMedidor)

                // Mensaje de éxito (Punto 7)
                Toast.makeText(requireContext(), "Registro guardado correctamente", Toast.LENGTH_SHORT).show()

                // Volvemos a la lista automáticamente
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
