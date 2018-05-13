package net.sytes.roneymaia.agenda.Model

class Contato {

    companion object {
        val TABLE_NAME = "contato"
        val COLUMN_ID = "id"
        val COLUMN_NOME = "nome"
        val COLUMN_TELEFONE = "telefone"
        val COLUMN_EMAIL = "email"
        val COLUMN_TIMESTAMP = "timestamp"
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOME + " TEXT,"
                + COLUMN_TELEFONE + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")")
    }
        var id: Int = 0
        var nome: String = ""
        var telefone: String = ""
        var email: String = ""
        var timestamp: String = ""

    constructor(id: Int, nome: String, telefone: String, email: String, timestamp: String) {
        this.id = id
        this.nome = nome
        this.telefone = telefone
        this.email = email
        this.timestamp = timestamp
    }
}