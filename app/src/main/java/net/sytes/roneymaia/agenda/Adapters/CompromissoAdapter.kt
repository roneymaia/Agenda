package net.sytes.roneymaia.agenda.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.sytes.roneymaia.agenda.Model.Compromisso
import net.sytes.roneymaia.agenda.R
import java.text.ParseException

import java.text.SimpleDateFormat

class CompromissoAdapter(context: Context, compromissos: List<Compromisso>): RecyclerView.Adapter<CompromissoAdapter.MyViewHolder>() {

    var context: Context? = null
    var compromissos: List<Compromisso>? = arrayListOf()

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var compromisso: TextView? = null
        var descricao: TextView? = null
        var data: TextView? = null
        var hora: TextView? = null

        init {
            this@MyViewHolder.compromisso = view.findViewById(R.id.compromisso) as TextView
            this@MyViewHolder.descricao = view.findViewById(R.id.descricao) as TextView
            this@MyViewHolder.data = view.findViewById(R.id.data) as TextView
            this@MyViewHolder.hora = view.findViewById(R.id.hora) as TextView
        }
    }


    init {
        this@CompromissoAdapter.context = context
        this@CompromissoAdapter.compromissos = compromissos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.compromisso, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val compromisso = compromissos!!.get(position)

        holder.compromisso!!.setText(compromisso.id)
        holder.descricao!!.setText(compromisso.descricao)
        holder.data!!.setText(compromisso.data)
        holder.hora!!.setText(compromisso.hora)
    }

    override fun getItemCount(): Int {
        return compromissos!!.size
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