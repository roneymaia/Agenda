package net.sytes.roneymaia.agenda.Model

class Login {

    companion object {
        val TABLE_NAME = "login"
        val COLUMN_ID = "id"
        val COLUMN_LOGIN = "login"
        val COLUMN_SENHA = "senha"
        val COLUMN_TIMESTAMP = "timestamp"
        val CREATE_TABLE = ("CREATE TABLE " + Companion.TABLE_NAME + "("
                + Companion.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Companion.COLUMN_LOGIN + " TEXT,"
                + Companion.COLUMN_SENHA + " TEXT,"
                + Companion.COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")")
    }

    var id: Int = 0
    var login: String = ""
    var senha: String = ""
    var timestamp: String = ""

    constructor(id: Int, login: String, senha: String, timestamp: String) {
        this.id = id
        this.login = login
        this.senha = senha
        this.timestamp = timestamp
    }
}