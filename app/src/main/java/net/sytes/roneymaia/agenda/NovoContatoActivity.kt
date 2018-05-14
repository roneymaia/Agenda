package net.sytes.roneymaia.agenda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import net.sytes.roneymaia.agenda.Database.DatabaseHelper
import net.sytes.roneymaia.agenda.Model.Contato
import net.sytes.roneymaia.agenda.Singletons.DbSingleton
import net.sytes.roneymaia.agenda.Utils.AgendaUtils
import net.sytes.roneymaia.agenda.Utils.AgendaValidator

class NovoContatoActivity : AppCompatActivity() {

    var nome: EditText? = null
    var telefone: EditText? = null
    var email: EditText? = null
    var adicionar: Button? = null
    var db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_contato)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this@NovoContatoActivity.db = DatabaseHelper(this@NovoContatoActivity)

        this@NovoContatoActivity.nome = findViewById(R.id.txtNovoContatoNome) as EditText

        this@NovoContatoActivity.telefone = findViewById(R.id.txtNovoContatoTelefone) as EditText

        this@NovoContatoActivity.email = findViewById(R.id.txtNovoContatoEmail) as EditText

        this@NovoContatoActivity.adicionar = findViewById(R.id.btnNovoContatoAdicionar) as Button

        this@NovoContatoActivity.adicionar!!.setOnClickListener{ view ->

            val nome = this@NovoContatoActivity.nome?.text.toString().trim()
            val telefone = this@NovoContatoActivity.telefone?.text.toString().trim()
            val email = this@NovoContatoActivity.email?.text.toString().trim()
            val isValid = AgendaValidator.isEmpty(arrayOf(nome, telefone, email))

            if(isValid){

                val values = hashMapOf(Contato.COLUMN_NOME to nome, Contato.COLUMN_TELEFONE to telefone, Contato.COLUMN_EMAIL to email)
                val id = this@NovoContatoActivity.db!!.insertRow(Contato.TABLE_NAME, values)

                DbSingleton.idLong = id // Atribui o id ao singleton

                if(id > 0){
                    AgendaUtils.toast(this@NovoContatoActivity, "Contato inserido com sucesso!")
                }else{
                    AgendaUtils.toast(this@NovoContatoActivity, "Falha ao inserir o contato, verifique os campos!")
                }

            }else{
                AgendaUtils.toast(this@NovoContatoActivity, "Falha ao inserir o contato, verifique os campos!")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean{
        this@NovoContatoActivity.finish()
        return true
    }
}
