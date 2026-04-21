package br.com.devandroid.registrodeeventosroom.data.repository

import br.com.devandroid.registrodeeventosroom.data.model.Evento

interface IRepositoryEvento {
    suspend fun salvar(evento: Evento): Long
    suspend fun delete(evento: Evento): Int
    suspend fun update(evento: Evento): Int
    suspend fun listarEventos(): List<Evento>

}