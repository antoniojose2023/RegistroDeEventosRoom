package br.com.devandroid.registrodeeventosroom.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.devandroid.registrodeeventosroom.data.database.DataBase
import br.com.devandroid.registrodeeventosroom.data.model.Evento
import com.google.common.truth.Truth
import dagger.hilt.android.internal.Contexts
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EventoDaoTest {

    private lateinit var bandoDeDados: DataBase
    private lateinit var eventoDAO: EventoDAO

   // private lateinit var context: Context

    @Before
    fun setUp() {

        //context = mock(Context::class.java)

        bandoDeDados = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DataBase::class.java
        )
        .allowMainThreadQueries()
        .build()

        eventoDAO = bandoDeDados.eventoDao()

    }

    @Test
    fun salvarevento_verificareventocadastrado_retornetrue() {
        val evento = Evento(null, "teste1", "02/05/2026", "conteudo de teste")
        val idEvento = eventoDAO.salvar(evento)

        Truth.assertThat(idEvento).isGreaterThan(0L)
    }

    @Test
    fun deleteEvento_verificareventodeletado_retornetrue() {
        salvarevento_verificareventocadastrado_retornetrue()
        val evento = Evento(1L, "teste1", "02/05/2026", "conteudo de teste")
        val linhasDeletada = eventoDAO.deletar( evento )

        Truth.assertThat(linhasDeletada).isEqualTo(1)
    }

    @Test
    fun updateEvento_verificareventoatualizado_retornetrue() {
        salvarevento_verificareventocadastrado_retornetrue()
        val evento = Evento(1L, "teste1", "02/05/2026", "conteudo de teste")
        val linhasDeletada = eventoDAO.atualizar( evento )

        Truth.assertThat(linhasDeletada).isEqualTo(1)
    }

    @Test
    fun listareventos_verificareventolistados_retornetrue() {
         salvarevento_verificareventocadastrado_retornetrue()

         val eventos = eventoDAO.listarEventos()
         Truth.assertThat(eventos).isNotEmpty()

    }

    @After
    fun tearDown() {
          bandoDeDados.close()
    }
}


