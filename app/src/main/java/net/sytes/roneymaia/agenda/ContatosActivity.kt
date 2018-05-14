package net.sytes.roneymaia.agenda

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import net.sytes.roneymaia.agenda.Database.DatabaseHelper
import android.view.View
import android.widget.Button
import net.sytes.roneymaia.agenda.Adapters.ContatoAdapter
import net.sytes.roneymaia.agenda.Model.Contato
import net.sytes.roneymaia.agenda.Singletons.DbSingleton
import net.sytes.roneymaia.agenda.Utils.AgendaUtils
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import net.sytes.roneymaia.agenda.Singletons.ContatoSingleton


class ContatosActivity : AppCompatActivity() {

    var contatoAdapter: ContatoAdapter? = null
    var contatosList: ArrayList<Contato> = ArrayList()
    var recyclerView: RecyclerView? = null
    var db: DatabaseHelper? = null
    var btnContato: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this@ContatosActivity.recyclerView = findViewById(R.id.recycler_view) as RecyclerView

        this@ContatosActivity.btnContato = findViewById(R.id.btnContatosNovo) as Button

        this@ContatosActivity.db = DatabaseHelper(this@ContatosActivity)

        this@ContatosActivity.contatosList.addAll(db!!.getAllRowsObjs(Contato.TABLE_NAME) as ArrayList<Contato>)

        this@ContatosActivity.contatoAdapter = ContatoAdapter(this@ContatosActivity, this@ContatosActivity.contatosList)

        this@ContatosActivity.btnContato!!.setOnClickListener{ view ->

            startActivity(Intent(this@ContatosActivity, NovoContatoActivity::class.java))

        }

        val mLayoutManager = LinearLayoutManager(this@ContatosActivity)

        this@ContatosActivity.recyclerView!!.layoutManager = mLayoutManager
        this@ContatosActivity.recyclerView!!.itemAnimator = DefaultItemAnimator()
        this@ContatosActivity.recyclerView!!.addItemDecoration(MyDividerItemDecoration(this@ContatosActivity, LinearLayoutManager.VERTICAL, 16))
        this@ContatosActivity.recyclerView!!.adapter = this@ContatosActivity.contatoAdapter

        altEmptyContatos()

        val listenerRecycler = object :  RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                val contato = this@ContatosActivity.contatosList!![position]

                ContatoSingleton.nome = contato.nome
                ContatoSingleton.telefone = contato.telefone
                ContatoSingleton.email = contato.email

                startActivity(Intent(this@ContatosActivity, EditarContatoActivity::class.java))
            }

            override fun onLongClick(view: View?, position: Int) {
                this@ContatosActivity.showDeleteDialog(position)
            }

        }

        this@ContatosActivity.recyclerView!!.addOnItemTouchListener(RecyclerTouchListener(
                this@ContatosActivity,
                this@ContatosActivity.recyclerView!!,
                listenerRecycler
                ))

    }


    fun altEmptyContatos() {
        // you can check notesList.size() > 0

        if (this@ContatosActivity.db!!.getTabCountObjs(Contato.TABLE_NAME) > 0) {
            //noNotesView.setVisibility(View.GONE)
        } else {
            //noNotesView.setVisibility(View.VISIBLE)
        }
    }

    override fun onResume() {
        val id = DbSingleton.idLong

        if(id!! > 0){
            val contato = this@ContatosActivity.db!!.getRowObj(Contato.TABLE_NAME, id!!)
            this@ContatosActivity.contatosList!!.add(contato as Contato)
        }
        this@ContatosActivity.contatoAdapter!!.notifyDataSetChanged()
        super.onResume()
    }

    fun showDeleteDialog(position: Int) {
        val colors = arrayOf<CharSequence>("Sim", "Não")

        val builder = AlertDialog.Builder(this@ContatosActivity)
        builder.setTitle("Deletar Contato")
        builder.setItems(colors, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                this@ContatosActivity.db!!.deleteRowObj(Contato.TABLE_NAME, this@ContatosActivity.contatosList!![position])

                this@ContatosActivity.contatosList!!.removeAt(position)
                this@ContatosActivity.contatoAdapter!!.notifyDataSetChanged()

                altEmptyContatos()
            } else {

            }
        })
        builder.show()
    }

    override fun onSupportNavigateUp(): Boolean{
        this@ContatosActivity.finish()
        return true
    }
}
