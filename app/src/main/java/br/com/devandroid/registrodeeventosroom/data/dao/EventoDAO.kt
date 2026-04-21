package br.com.devandroid.registrodeeventosroom.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.devandroid.registrodeeventosroom.data.model.Evento

@Dao
interface EventoDAO {

        @Insert
        fun salvar(evento: Evento): Long

        @Update
        fun atualizar(evento: Evento): Int

        @Delete
        fun deletar(evento: Evento): Int

        @Query("SELECT * FROM eventos")
        fun listarEventos(): List<Evento>

}