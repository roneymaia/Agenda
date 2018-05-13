package net.sytes.roneymaia.agenda.Utils

class AgendaValidator {

    companion object {

        fun isEmpty(campos: Array<String>): Boolean{

            for (campo in campos){
                if (campo.isEmpty()){
                    return false
                }
            }

            return true
        }


    }

}