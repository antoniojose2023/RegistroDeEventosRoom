package br.com.devandroid.registrodeeventosroom.di

import android.content.Context
import br.com.devandroid.registrodeeventosroom.data.dao.EventoDAO
import br.com.devandroid.registrodeeventosroom.data.database.DataBase
import br.com.devandroid.registrodeeventosroom.data.repository.IRepositoryEvento
import br.com.devandroid.registrodeeventosroom.data.repository.RepositoryEventoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providerDatabase(@ApplicationContext context: Context): DataBase{
          return DataBase.getInstance( context )
    }

    @Provides
    fun providerEventoDao(database: DataBase): EventoDAO {
          return database.eventoDao()
    }

    @Provides
    fun providerRepositoryEvento(eventoDAO: EventoDAO): IRepositoryEvento{
          return RepositoryEventoImpl(eventoDAO)
    }

}