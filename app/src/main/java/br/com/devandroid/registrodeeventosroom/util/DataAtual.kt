package br.com.devandroid.registrodeeventosroom.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object DataAtual {

    @SuppressLint("SimpleDateFormat")
    fun getDataHoraAtual(): String{
        val date = Date()
        val formate = SimpleDateFormat("dd/MM/YYYY - HH:MM:ss")

        return formate.format( date )
    }

    @SuppressLint("SimpleDateFormat")
    fun getDataAtual(): String{
        val date = Date()
        val formate = SimpleDateFormat("dd/MM/YYYY")

        return formate.format( date )
    }

    fun getDiaAtual(dataAtual: String): String{

        val dia = dataAtual.split("/")
        return dia[0].toString()
    }

    fun getMesAtual(dataAtual: String): String{

        val dia = dataAtual.split("/")
        return dia[1]
    }

    fun getAnoAtual(dataAtual: String): String{

        val dia = dataAtual.split("/")
        return dia[2].toString()
    }

    fun getNomeMes(data: String): String{
        val mesRecuperado = data.split("/")[1]

        return when(mesRecuperado){
            "01" -> {"janeiro"}
            "02" -> {"fevereiro"}
            "03" -> {"marco"}
            "04" -> {"abril"}
            "05" -> {"maio"}
            "06" -> {"junho"}
            "07" -> {"julho"}
            "08" -> {"agosto"}
            "09" -> {"setemebro"}
            "10" -> {"outubro"}
            "11" -> {"novembro"}
            "12" -> {"dezembro"}
            else -> {"mes inválido"}
        }

    }

}