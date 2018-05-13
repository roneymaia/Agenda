package net.sytes.roneymaia.agenda.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import net.sytes.roneymaia.agenda.Model.Compromisso
import net.sytes.roneymaia.agenda.Model.Contato
import net.sytes.roneymaia.agenda.Model.Login
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ravi on 15/03/18.
 */

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "agenda", null, 1) {

    val DATABASE_TABELAS = arrayOf(Login.TABLE_NAME, Contato.TABLE_NAME, Compromisso.TABLE_NAME)
    val DATABASE_CREATE = arrayOf(Login.CREATE_TABLE, Contato.CREATE_TABLE, Compromisso.CREATE_TABLE)

    override fun onCreate(db: SQLiteDatabase) {
        // Cria as tabelas dos bancos de dados
        for (tabela in DATABASE_CREATE) db.execSQL(tabela)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,newVersion: Int) {
        // Drop older table if existed
        for (tabela in DATABASE_TABELAS) db.execSQL(tabela)
        // Cria novamente as tabelas
        onCreate(db)
    }

    fun insertRow(tabela: String, dados: HashMap<String, String>) : Long{

        val db = this.writableDatabase
        val values = ContentValues()
        var id: Long = 0

        when(tabela){
            Login.TABLE_NAME -> {
                values.put(Login.COLUMN_LOGIN, dados.get(Login.COLUMN_LOGIN))
                values.put(Login.COLUMN_SENHA, dados.get(Login.COLUMN_SENHA))
                id = db.insert(Login.TABLE_NAME, null, values)
            }

            Contato.TABLE_NAME -> {
                values.put(Contato.COLUMN_NOME, dados.get(Contato.COLUMN_NOME))
                values.put(Contato.COLUMN_TELEFONE, dados.get(Contato.COLUMN_TELEFONE))
                values.put(Contato.COLUMN_EMAIL, dados.get(Contato.COLUMN_EMAIL))
                id = db.insert(Contato.TABLE_NAME, null, values)
            }
            Compromisso.TABLE_NAME -> {
                values.put(Compromisso.COLUMN_DESCRICAO, dados.get(Compromisso.COLUMN_DESCRICAO))
                values.put(Compromisso.COLUMN_DATA, dados.get(Compromisso.COLUMN_DATA))
                values.put(Compromisso.COLUMN_HORA, dados.get(Compromisso.COLUMN_HORA))
                id = db.insert(Compromisso.TABLE_NAME, null, values)
            }
        }

        db.close()
        return id
    }

    // Obtem os dados de cada tabela por objeto
    fun getRowObj(tabela: String, id: Long): Any? {

        val db = this.readableDatabase

        when (tabela) {
            Login.TABLE_NAME -> {

                val cursor = db.query(Login.TABLE_NAME,
                        arrayOf(Login.COLUMN_ID, Login.COLUMN_LOGIN, Login.COLUMN_SENHA, Login.COLUMN_TIMESTAMP),
                        Login.COLUMN_ID + "=?",
                        arrayOf(id.toString()), null, null, null, null)

                if (cursor != null && cursor.moveToFirst()) {

                    val login = Login(
                            cursor.getInt(cursor.getColumnIndex(Login.COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(Login.COLUMN_LOGIN)),
                            cursor.getString(cursor.getColumnIndex(Login.COLUMN_SENHA)),
                            cursor.getString(cursor.getColumnIndex(Login.COLUMN_TIMESTAMP)))

                    cursor.close()
                    db.close()
                    return login
                }
            }

            Contato.TABLE_NAME -> {
                val cursor = db.query(Contato.TABLE_NAME,
                        arrayOf(Contato.COLUMN_ID, Contato.COLUMN_NOME, Contato.COLUMN_TELEFONE, Contato.COLUMN_EMAIL, Contato.COLUMN_TIMESTAMP),
                        Contato.COLUMN_ID + "=?",
                        arrayOf(id.toString()), null, null, null, null)

                if (cursor != null && cursor.moveToFirst()) {

                    val contato = Contato(
                            cursor.getInt(cursor.getColumnIndex(Contato.COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(Contato.COLUMN_NOME)),
                            cursor.getString(cursor.getColumnIndex(Contato.COLUMN_TELEFONE)),
                            cursor.getString(cursor.getColumnIndex(Contato.COLUMN_EMAIL)),
                            cursor.getString(cursor.getColumnIndex(Contato.COLUMN_TIMESTAMP)))

                    cursor.close()
                    db.close()
                    return contato
                }
            }
            Compromisso.TABLE_NAME -> {
                val cursor = db.query(Compromisso.TABLE_NAME,
                        arrayOf(Compromisso.COLUMN_ID, Compromisso.COLUMN_DESCRICAO, Compromisso.COLUMN_DATA, Compromisso.COLUMN_HORA, Compromisso.COLUMN_TIMESTAMP),
                        Compromisso.COLUMN_ID + "=?",
                        arrayOf(id.toString()), null, null, null, null)

                if (cursor != null && cursor.moveToFirst()) {

                    val compromisso = Compromisso(
                            cursor.getInt(cursor.getColumnIndex(Compromisso.COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(Compromisso.COLUMN_DESCRICAO)),
                            cursor.getString(cursor.getColumnIndex(Compromisso.COLUMN_DATA)),
                            cursor.getString(cursor.getColumnIndex(Compromisso.COLUMN_HORA)),
                            cursor.getString(cursor.getColumnIndex(Compromisso.COLUMN_TIMESTAMP)))

                    cursor.close()
                    db.close()
                    return compromisso
                }
            }
        }

        db.close()
        return null
    }

    // Obtem os dados de todos os objetos
    fun getAllRowsObjs(tabela: String): List<Any> {

        val db = this.readableDatabase
        var objetos: ArrayList<Any> = ArrayList<Any>()

        when(tabela){
            Login.TABLE_NAME -> {
                val selectQuery = "SELECT * FROM " + Login.TABLE_NAME + " ORDER BY " +
                        Login.COLUMN_TIMESTAMP + " DESC"

                val cursor = db.rawQuery(selectQuery, null)

                if (cursor != null && cursor.moveToFirst()){
                    do{
                        objetos.add(
                                Login(
                                        cursor.getInt(cursor.getColumnIndex(Login.COLUMN_ID)),
                                        cursor.getString(cursor.getColumnIndex(Login.COLUMN_LOGIN)),
                                        cursor.getString(cursor.getColumnIndex(Login.COLUMN_SENHA)),
                                        cursor.getString(cursor.getColumnIndex(Login.COLUMN_TIMESTAMP))
                                     )
                        )
                    }while(cursor.moveToNext())

                    cursor.close()
                    db.close()
                }
            }

            Contato.TABLE_NAME -> {
                val selectQuery = "SELECT * FROM " + Contato.TABLE_NAME + " ORDER BY " +
                        Contato.COLUMN_TIMESTAMP + " DESC"

                val cursor = db.rawQuery(selectQuery, null)

                if (cursor != null && cursor.moveToFirst()){
                    do{
                        objetos.add(
                                Contato(
                                        cursor.getInt(cursor.getColumnIndex(Contato.COLUMN_ID)),
                                        cursor.getString(cursor.getColumnIndex(Contato.COLUMN_NOME)),
                                        cursor.getString(cursor.getColumnIndex(Contato.COLUMN_TELEFONE)),
                                        cursor.getString(cursor.getColumnIndex(Contato.COLUMN_EMAIL)),
                                        cursor.getString(cursor.getColumnIndex(Contato.COLUMN_TIMESTAMP))
                                )
                        )
                    }while(cursor.moveToNext())

                    cursor.close()
                    db.close()
                }
            }
            Compromisso.TABLE_NAME -> {
                val selectQuery = "SELECT * FROM " + Compromisso.TABLE_NAME + " ORDER BY " +
                        Compromisso.COLUMN_TIMESTAMP + " DESC"

                val cursor = db.rawQuery(selectQuery, null)

                if (cursor != null && cursor.moveToFirst()){
                    do{
                        objetos.add(
                                Compromisso(
                                        cursor.getInt(cursor.getColumnIndex(Compromisso.COLUMN_ID)),
                                        cursor.getString(cursor.getColumnIndex(Compromisso.COLUMN_DESCRICAO)),
                                        cursor.getString(cursor.getColumnIndex(Compromisso.COLUMN_DATA)),
                                        cursor.getString(cursor.getColumnIndex(Compromisso.COLUMN_HORA)),
                                        cursor.getString(cursor.getColumnIndex(Compromisso.COLUMN_TIMESTAMP))
                                )
                        )
                    }while(cursor.moveToNext())

                    cursor.close()
                    db.close()
                }
            }
        }

        return objetos

    }

    fun getTabCountObjs(tabela: String): Int {

        val db = this.readableDatabase
        var count: Int = 0

        when(tabela){
            Login.TABLE_NAME -> {
                val countQuery = "SELECT * FROM " + Login.TABLE_NAME
                val cursor = db.rawQuery(countQuery, null)

                if (cursor != null){
                    count = cursor.count
                    cursor.close()
                }
            }

            Contato.TABLE_NAME -> {
                val countQuery = "SELECT * FROM " + Contato.TABLE_NAME
                val cursor = db.rawQuery(countQuery, null)

                if (cursor != null){
                    count = cursor.count
                    cursor.close()
                }
            }
            Compromisso.TABLE_NAME -> {
                val countQuery = "SELECT * FROM " + Compromisso.TABLE_NAME
                val cursor = db.rawQuery(countQuery, null)

                if (cursor != null){
                    count = cursor.count
                    cursor.close()
                }
            }
        }

        db.close()
        return count
    }

    fun updateRowObj(tabela: String, objeto: Any): Int {

        val db = this.writableDatabase
        val values = ContentValues()

        when(tabela){
            Login.TABLE_NAME -> {
                val login = objeto as Login
                values.put(Login.COLUMN_LOGIN, login.login)
                values.put(Login.COLUMN_SENHA, login.senha)

                db.close()
                return db.update(Login.TABLE_NAME, values, Login.COLUMN_ID + " = ?",
                        arrayOf(login.id.toString()))
            }

            Contato.TABLE_NAME -> {
                val contato = objeto as Contato

                values.put(Contato.COLUMN_NOME, contato.nome)
                values.put(Contato.COLUMN_TELEFONE, contato.telefone)
                values.put(Contato.COLUMN_EMAIL, contato.email)

                db.close()
                return db.update(Contato.TABLE_NAME, values, Contato.COLUMN_ID + " = ?",
                        arrayOf(contato.id.toString()))
            }
            Compromisso.TABLE_NAME -> {

                val compromisso = objeto as Compromisso

                values.put(Compromisso.COLUMN_DESCRICAO, compromisso.descricao)
                values.put(Compromisso.COLUMN_DATA, compromisso.data)
                values.put(Compromisso.COLUMN_HORA, compromisso.hora)

                db.close()
                return db.update(Compromisso.TABLE_NAME, values, Compromisso.COLUMN_ID + " = ?",
                        arrayOf(compromisso.id.toString()))

            }
        }

        db.close()
        return 0
    }

    fun deleteRowObj(tabela: String, objeto: Any) {

        val db = this.writableDatabase

        when(tabela){
            Login.TABLE_NAME -> {
                val login = objeto as Login
                db.delete(Login.TABLE_NAME, Login.COLUMN_ID + " = ?",
                        arrayOf(login.id.toString()))
            }

            Contato.TABLE_NAME -> {
                val contato = objeto as Login
                db.delete(Contato.TABLE_NAME, Contato.COLUMN_ID + " = ?",
                        arrayOf(contato.id.toString()))
            }
            Compromisso.TABLE_NAME -> {
                val compromisso = objeto as Login
                db.delete(Compromisso.TABLE_NAME, Compromisso.COLUMN_ID + " = ?",
                        arrayOf(compromisso.id.toString()))
            }
        }

        db.close()
    }

}