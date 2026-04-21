package br.com.devandroid.registrodeeventosroom.data.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.devandroid.registrodeeventosroom.data.dao.EventoDAO
import br.com.devandroid.registrodeeventosroom.data.model.Evento

@Database(entities = [Evento::class], version = 1)
abstract class DataBase: RoomDatabase() {

   abstract fun eventoDao(): EventoDAO

   companion object{

       fun getInstance(context: Context): DataBase{
              return Room.databaseBuilder(
                     context,
                     DataBase::class.java,
                     "evento.db"
              ).build()
       }

   }

}