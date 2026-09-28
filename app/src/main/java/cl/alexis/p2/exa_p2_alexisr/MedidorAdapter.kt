package cl.alexis.p2.exa_p2_alexisr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedidorAdapter(private var medidores: List<Medidor>) :
    RecyclerView.Adapter<MedidorAdapter.MedidorViewHolder>() {

    class MedidorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcono: ImageView = view.findViewById(R.id.ivIcono)
        val tvTipo: TextView = view.findViewById(R.id.tvTipo)
        val tvValor: TextView = view.findViewById(R.id.tvValor)
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedidorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medidor, parent, false)
        return MedidorViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedidorViewHolder, position: Int) {
        val medidor = medidores[position]

        holder.tvTipo.text = medidor.tipo.uppercase()
        holder.tvValor.text = medidor.valor.toString()
        holder.tvFecha.text = medidor.fecha

        val iconoRes = when (medidor.tipo.lowercase()) {
            "agua" -> R.drawable.ic_agua
            "luz" -> R.drawable.ic_luz
            "gas" -> R.drawable.ic_gas
            else -> R.drawable.ic_luz
        }
        holder.ivIcono.setImageResource(iconoRes)
    }

    override fun getItemCount() = medidores.size

    fun actualizarLista(nuevaLista: List<Medidor>) {
        this.medidores = nuevaLista
        notifyDataSetChanged()
    }
}