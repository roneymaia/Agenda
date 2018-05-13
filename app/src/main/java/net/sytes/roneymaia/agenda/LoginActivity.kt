package net.sytes.roneymaia.agenda

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import net.sytes.roneymaia.agenda.Database.DatabaseHelper
import net.sytes.roneymaia.agenda.Model.Login
import net.sytes.roneymaia.agenda.Utils.AgendaUtils
import net.sytes.roneymaia.agenda.Utils.AgendaValidator

class LoginActivity : AppCompatActivity() {

    var login: TextView? = null
    var senha: TextView? = null
    var entrar: Button? = null
    var cadastrar: Button? = null
    var db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this@LoginActivity.login = findViewById(R.id.txtLoginNome) as TextView
        this@LoginActivity.senha = findViewById(R.id.txtLoginSenha) as TextView
        this@LoginActivity.entrar = findViewById(R.id.btnLoginEntrar) as Button
        this@LoginActivity.cadastrar = findViewById(R.id.btnLoginCadastrar) as Button

        this@LoginActivity.db = DatabaseHelper(this@LoginActivity)

        this@LoginActivity.entrar!!.setOnClickListener( { view ->

            val login = this@LoginActivity.login!!.text.toString().trim()
            val senha = this@LoginActivity.senha!!.text.toString().trim()
            val isValid = AgendaValidator.isEmpty(arrayOf(login, senha))

            if(isValid){
                val hasRow = this@LoginActivity.db!!.checkRow(Login.TABLE_NAME, 0, login, senha)

                if(hasRow){
                    startActivity(Intent(this@LoginActivity, AgendaActivity::class.java))
                }else{
                    AgendaUtils.toast(this@LoginActivity, "Login ou senha, inválidos!")
                }

            }else{
                AgendaUtils.toast(this@LoginActivity, "Login ou senha, inválidos!")
            }
        })

        this@LoginActivity.cadastrar!!.setOnClickListener { view ->

            startActivity(Intent(this@LoginActivity, CadastrarLoginActivity::class.java))
        }


    }
}
