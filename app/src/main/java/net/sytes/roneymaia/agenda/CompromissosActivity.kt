package net.sytes.roneymaia.agenda

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import net.sytes.roneymaia.agenda.Adapters.CompromissoAdapter
import net.sytes.roneymaia.agenda.Database.DatabaseHelper
import net.sytes.roneymaia.agenda.Model.Compromisso
import net.sytes.roneymaia.agenda.Singletons.DbSingleton

class CompromissosActivity : AppCompatActivity() {

    var compromissoAdapter: CompromissoAdapter? = null
    var compromissoList: ArrayList<Compromisso> = ArrayList()
    var recyclerView: RecyclerView? = null
    var db: DatabaseHelper? = null
    var btnCompromisso: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compromissos)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this@CompromissosActivity.recyclerView = findViewById(R.id.recycler_view_compromisso) as RecyclerView

        this@CompromissosActivity.btnCompromisso = findViewById(R.id.btnCompromissosNovo) as Button

        this@CompromissosActivity.db = DatabaseHelper(this@CompromissosActivity)

        this@CompromissosActivity.compromissoList.addAll(db!!.getAllRowsObjs(Compromisso.TABLE_NAME) as ArrayList<Compromisso>)

        this@CompromissosActivity.compromissoAdapter = CompromissoAdapter(this@CompromissosActivity, this@CompromissosActivity.compromissoList)

        this@CompromissosActivity.btnCompromisso!!.setOnClickListener{ view ->

            startActivity(Intent(this@CompromissosActivity, NovoCompromissoActivity::class.java))

        }

        val mLayoutManager = LinearLayoutManager(this@CompromissosActivity)

        this@CompromissosActivity.recyclerView!!.layoutManager = mLayoutManager
        this@CompromissosActivity.recyclerView!!.itemAnimator = DefaultItemAnimator()
        this@CompromissosActivity.recyclerView!!.addItemDecoration(MyDividerItemDecoration(this@CompromissosActivity, LinearLayoutManager.VERTICAL, 16))
        this@CompromissosActivity.recyclerView!!.adapter = this@CompromissosActivity.compromissoAdapter

        altEmptyCompromisso()

        val listenerRecycler = object :  RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                //val contato = this@CompromissosActivity.compromissoList!![position]

                //CompromissoSingleton.nome = contato.nome
                //CompromissoSingleton.telefone = contato.telefone
                //CompromissoSingleton.email = contato.email

                //startActivity(Intent(this@CompromissosActivity, EditarCompromissoActivity::class.java))
            }

            override fun onLongClick(view: View?, position: Int) {
                this@CompromissosActivity.showDeleteDialog(position)
            }

        }

        this@CompromissosActivity.recyclerView!!.addOnItemTouchListener(RecyclerTouchListener(
                this@CompromissosActivity,
                this@CompromissosActivity.recyclerView!!,
                listenerRecycler
        ))

    }


    fun altEmptyCompromisso() {

        if (this@CompromissosActivity.db!!.getTabCountObjs(Compromisso.TABLE_NAME) > 0) {
            //noNotesView.setVisibility(View.GONE)
        } else {
            //noNotesView.setVisibility(View.VISIBLE)
        }
    }

    override fun onResume() {
        val id = DbSingleton.idLong

        if(id!! > 0){
            val compromisso = this@CompromissosActivity.db!!.getRowObj(Compromisso.TABLE_NAME, id!!)
            this@CompromissosActivity.compromissoList!!.add(compromisso as Compromisso)
        }
        this@CompromissosActivity.compromissoAdapter!!.notifyDataSetChanged()
        super.onResume()
    }

    fun showDeleteDialog(position: Int) {
        val colors = arrayOf<CharSequence>("Sim", "Não")

        val builder = AlertDialog.Builder(this@CompromissosActivity)
        builder.setTitle("Deletar Compromisso")
        builder.setItems(colors, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                this@CompromissosActivity.db!!.deleteRowObj(Compromisso.TABLE_NAME, this@CompromissosActivity.compromissoList!![position])

                this@CompromissosActivity.compromissoList!!.removeAt(position)
                this@CompromissosActivity.compromissoAdapter!!.notifyDataSetChanged()

                altEmptyCompromisso()
            } else {

            }
        })
        builder.show()
    }

    override fun onSupportNavigateUp(): Boolean{
        this@CompromissosActivity.finish()
        return true
    }

    override fun onDestroy() {
        DbSingleton.idLong = 0
        super.onDestroy()
    }
}
