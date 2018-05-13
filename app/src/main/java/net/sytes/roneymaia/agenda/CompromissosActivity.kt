package net.sytes.roneymaia.agenda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class CompromissosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compromissos)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean{
        this@CompromissosActivity.finish()
        return true
    }
}
