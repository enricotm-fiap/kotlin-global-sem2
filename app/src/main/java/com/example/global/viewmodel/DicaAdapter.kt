import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.global.R
import com.example.global.model.Dica

class DicaAdapter(private val dicas: List<Dica>, private val onClick: (Dica) -> Unit) :
    RecyclerView.Adapter<DicaAdapter.DicaViewHolder>() {

    class DicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.titulo)
        val descricao: TextView = view.findViewById(R.id.descricao)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dica, parent, false)
        return DicaViewHolder(view)
    }

    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val dica = dicas[position]
        holder.titulo.text = dica.titulo
        holder.descricao.text = dica.descricao
        holder.itemView.setOnClickListener { onClick(dica) }
    }

    override fun getItemCount(): Int = dicas.size

    fun updateData(newDicas: List<Dica>) {
        // dicas = newDicas notifyDataSetChanged()
    }
}
