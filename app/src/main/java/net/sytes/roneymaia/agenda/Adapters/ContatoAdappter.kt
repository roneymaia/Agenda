package net.sytes.roneymaia.agenda.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.sytes.roneymaia.agenda.Model.Contato
import net.sytes.roneymaia.agenda.R
import java.text.ParseException

import java.text.SimpleDateFormat

class ContatoAdapter(context: Context, contatos: List<Contato>): RecyclerView.Adapter<ContatoAdapter.MyViewHolder>() {

    var context: Context? = null
    var contatos: List<Contato>? = arrayListOf()

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var nome: TextView? = null
        var telefone: TextView? = null
        var email: TextView? = null

        init {
            this@MyViewHolder.nome = view.findViewById(R.id.nome) as TextView
            this@MyViewHolder.telefone = view.findViewById(R.id.telefone) as TextView
            this@MyViewHolder.email = view.findViewById(R.id.email) as TextView
        }
    }


    init {
        this@ContatoAdapter.context = context
        this@ContatoAdapter.contatos = contatos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.contatos, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contato = contatos!!.get(position)

        holder.nome!!.setText(contato.nome)
        holder.telefone!!.setText(contato.nome)
        holder.email!!.setText(contato.email)
    }

    override fun getItemCount(): Int {
        return contatos!!.size
    }


    fun formatDate(dateStr: String): String {
        try {
            val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = fmt.parse(dateStr)
            val fmtOut = SimpleDateFormat("MMM d")
            return fmtOut.format(date)
        } catch (e: ParseException) {

        }

        return ""
    }

}