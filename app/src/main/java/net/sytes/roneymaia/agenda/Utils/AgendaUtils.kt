package net.sytes.roneymaia.agenda.Utils

import android.content.Context
import android.widget.Toast

class AgendaUtils {

    companion object {

        fun toast(context: Context, msg: String){

            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()

        }

    }

}