package net.sytes.roneymaia.agenda.Model

class Compromisso {

    companion object {
        val TABLE_NAME = "compromisso"
        val COLUMN_ID = "id"
        val COLUMN_DESCRICAO = "descricao"
        val COLUMN_DATA = "data"
        val COLUMN_HORA = "hora"
        val COLUMN_TIMESTAMP = "timestamp"
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DESCRICAO + " TEXT,"
                + COLUMN_DATA + " TEXT,"
                + COLUMN_HORA + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")")
    }

    var id: Int = 0
    var descricao: String = ""
    var data: String = ""
    var hora: String = ""
    var timestamp: String = ""

    constructor(id: Int, descricao: String, data: String, hora: String, timestamp: String) {
        this.id = id
        this.descricao = descricao
        this.data = data
        this.hora = hora
        this.timestamp = timestamp
    }
}