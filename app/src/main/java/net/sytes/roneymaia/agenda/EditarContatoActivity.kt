package net.sytes.roneymaia.agenda

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.TextView
import net.sytes.roneymaia.agenda.Singletons.ContatoSingleton

class EditarContatoActivity : AppCompatActivity() {

    var nome: TextView? = null
    var ligar: TextView? = null
    var email: TextView? = null

    var nomeContato: String? = ""
    var telefoneContato: String? = ""
    var emailContato: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contato)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this@EditarContatoActivity.nome = findViewById(R.id.txtEditarContatoNome) as TextView
        this@EditarContatoActivity.ligar = findViewById(R.id. txtEditarContatoLigar) as TextView
        this@EditarContatoActivity.email = findViewById(R.id.txtEditarContatoEmail) as TextView


        this@EditarContatoActivity.nomeContato = ContatoSingleton.nome
        this@EditarContatoActivity.telefoneContato = ContatoSingleton.telefone
        this@EditarContatoActivity.emailContato = ContatoSingleton.email

        this@EditarContatoActivity.nome!!.text = this@EditarContatoActivity.nomeContato!!

        this@EditarContatoActivity.ligar!!.setOnClickListener{ view ->
            val checkPermission = ContextCompat.checkSelfPermission(this@EditarContatoActivity, Manifest.permission.CALL_PHONE)
            val REQUEST_CALL_PHONE = 1

            if (checkPermission == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + this@EditarContatoActivity.telefoneContato!!)))
            }

        }

        this@EditarContatoActivity.email!!.setOnClickListener{ view ->
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", this@EditarContatoActivity.emailContato!!, null)))
        }

    }

    override fun onSupportNavigateUp(): Boolean{
        this@EditarContatoActivity.finish()
        return true
    }
}
