package br.com.devandroid.registrodeeventosroom.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventos")
data class Evento(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val titulo: String = "",
    val data: String = "",
    val conteudo: String = ""
)