package net.sytes.roneymaia.agenda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import net.sytes.roneymaia.agenda.Database.DatabaseHelper
import net.sytes.roneymaia.agenda.Model.Login
import net.sytes.roneymaia.agenda.Utils.AgendaUtils
import net.sytes.roneymaia.agenda.Utils.AgendaValidator

class CadastrarLoginActivity : AppCompatActivity() {

    var login: EditText? = null
    var senha: EditText? = null
    var cadastrar: Button? = null
    var db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_login)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        this@CadastrarLoginActivity.login = findViewById(R.id.txtCadastrarLoginNome) as EditText
        this@CadastrarLoginActivity.senha = findViewById(R.id.txtCadastrarLoginSenha) as EditText
        this@CadastrarLoginActivity.cadastrar = findViewById(R.id.btnCadastrarLoginCadastrar) as Button

        this@CadastrarLoginActivity.db = DatabaseHelper(this@CadastrarLoginActivity)

        this@CadastrarLoginActivity.cadastrar!!.setOnClickListener{ view ->

            val login = this@CadastrarLoginActivity.login!!.text.toString().trim()
            val senha = this@CadastrarLoginActivity.senha!!.text.toString().trim()
            val isValid = AgendaValidator.isEmpty(arrayOf(login, senha))

            if(isValid){
                val id = this@CadastrarLoginActivity.db!!.insertRow(Login.TABLE_NAME,
                        hashMapOf(Login.COLUMN_LOGIN to  login,
                                    Login.COLUMN_SENHA to senha))

                if(id > 0){
                    AgendaUtils.toast(this@CadastrarLoginActivity, "Cadastro realizado com sucesso!")
                }

            }else{
                AgendaUtils.toast(this@CadastrarLoginActivity, "Login ou senha, inválidos!")
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean{
        this@CadastrarLoginActivity.finish()
        return true
    }


}
