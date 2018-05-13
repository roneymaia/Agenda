package net.sytes.roneymaia.agenda

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AgendaActivity : AppCompatActivity() {

    var contatos: TextView? = null
    var compromissos: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this@AgendaActivity.contatos = findViewById(R.id.txtAgendaContatos) as TextView
        this@AgendaActivity.compromissos  = findViewById(R.id.txtAgendaCompromissos) as TextView

        this@AgendaActivity.contatos!!.setOnClickListener { view ->
            startActivity(Intent(this@AgendaActivity, ContatosActivity::class.java))
        }

        this@AgendaActivity.compromissos!!.setOnClickListener { view ->
            startActivity(Intent(this@AgendaActivity, CompromissosActivity::class.java))
        }

    }

    override fun onSupportNavigateUp(): Boolean{
        this@AgendaActivity.finish()
        return true
    }

}
