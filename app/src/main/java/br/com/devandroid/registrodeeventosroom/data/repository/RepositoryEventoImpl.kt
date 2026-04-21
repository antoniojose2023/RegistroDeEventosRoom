package br.com.devandroid.registrodeeventosroom.data.repository

import br.com.devandroid.registrodeeventosroom.data.dao.EventoDAO
import br.com.devandroid.registrodeeventosroom.data.model.Evento
import javax.inject.Inject

class RepositoryEventoImpl @Inject constructor (private val eventoDAO: EventoDAO): IRepositoryEvento {
    override suspend fun salvar(evento: Evento): Long {
         return eventoDAO.salvar( evento )
    }

    override suspend fun delete(evento: Evento): Int {
        return eventoDAO.deletar( evento )
    }

    override suspend fun update(evento: Evento): Int {
        return eventoDAO.atualizar( evento )
    }

    override suspend fun listarEventos(): List<Evento> {
          return eventoDAO.listarEventos()
    }
}