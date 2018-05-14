package net.sytes.roneymaia.agenda

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import net.sytes.roneymaia.agenda.Database.DatabaseHelper
import net.sytes.roneymaia.agenda.Model.Login
import net.sytes.roneymaia.agenda.Utils.AgendaUtils
import net.sytes.roneymaia.agenda.Utils.AgendaValidator
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat



class LoginActivity : AppCompatActivity() {

    var login: EditText? = null
    var senha: EditText? = null
    var entrar: Button? = null
    var cadastrar: Button? = null
    var db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val checkPermission = ContextCompat.checkSelfPermission(this@LoginActivity, Manifest.permission.CALL_PHONE)
        val REQUEST_CALL_PHONE = 1

        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CALL_PHONE)
        }

        this@LoginActivity.login = findViewById(R.id.txtLoginNome) as EditText
        this@LoginActivity.senha = findViewById(R.id.txtLoginSenha) as EditText
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
