package net.sytes.roneymaia.agenda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import net.sytes.roneymaia.agenda.Database.DatabaseHelper
import net.sytes.roneymaia.agenda.Model.Compromisso
import net.sytes.roneymaia.agenda.Singletons.DbSingleton
import net.sytes.roneymaia.agenda.Utils.AgendaUtils
import net.sytes.roneymaia.agenda.Utils.AgendaValidator

class NovoCompromissoActivity : AppCompatActivity() {

    var descricao: EditText? = null
    var data: EditText? = null
    var hora: EditText? = null
    var adicionar: Button? = null
    var db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_compromisso)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this@NovoCompromissoActivity.db = DatabaseHelper(this@NovoCompromissoActivity)

        this@NovoCompromissoActivity.descricao = findViewById(R.id.txtNovoCompromissoDesc) as EditText

        this@NovoCompromissoActivity.data = findViewById(R.id.txtNovoCompromissoData) as EditText

        this@NovoCompromissoActivity.hora = findViewById(R.id.txtNovoCompromissoHora) as EditText

        this@NovoCompromissoActivity.adicionar = findViewById(R.id.btnNovoCompromissoAdd) as Button

        this@NovoCompromissoActivity.adicionar!!.setOnClickListener{ view ->

            val descricao = this@NovoCompromissoActivity.descricao?.text.toString().trim()
            val data = this@NovoCompromissoActivity.data?.text.toString().trim()
            val hora = this@NovoCompromissoActivity.hora?.text.toString().trim()
            val isValid = AgendaValidator.isEmpty(arrayOf(descricao, data, hora))

            if(isValid){

                val values = hashMapOf(Compromisso.COLUMN_DESCRICAO to descricao, Compromisso.COLUMN_DATA to data, Compromisso.COLUMN_HORA to hora)
                val id = this@NovoCompromissoActivity.db!!.insertRow(Compromisso.TABLE_NAME, values)

                DbSingleton.idLong = id // Atribui o id ao singleton

                if(id > 0){
                    AgendaUtils.toast(this@NovoCompromissoActivity, "Compromisso inserido com sucesso!")
                }else{
                    AgendaUtils.toast(this@NovoCompromissoActivity, "Falha ao inserir o contato, verifique os campos!")
                }

            }else{
                AgendaUtils.toast(this@NovoCompromissoActivity, "Falha ao inserir o contato, verifique os campos!")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean{
        this@NovoCompromissoActivity.finish()
        return true
    }
}
