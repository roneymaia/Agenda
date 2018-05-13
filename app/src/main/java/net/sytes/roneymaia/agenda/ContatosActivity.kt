package net.sytes.roneymaia.agenda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ContatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatos)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean{
        this@ContatosActivity.finish()
        return true
    }
}
