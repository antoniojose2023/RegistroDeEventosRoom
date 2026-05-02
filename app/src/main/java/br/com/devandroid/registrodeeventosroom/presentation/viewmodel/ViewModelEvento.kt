package br.com.devandroid.registrodeeventosroom.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devandroid.registrodeeventosroom.data.model.Evento
import br.com.devandroid.registrodeeventosroom.data.repository.IRepositoryEvento
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelEvento @Inject constructor (private val repositoryEvento: IRepositoryEvento) : ViewModel() {

    private val _eventos = MutableLiveData<List<Evento>>()
    val eventos: LiveData<List<Evento>> = _eventos

    suspend fun salvarEvento(evento: Evento) = repositoryEvento.salvar(evento)

    suspend fun deletarEvento(evento: Evento) = repositoryEvento.delete(evento)

    suspend fun atualizarEvento(evento: Evento) = repositoryEvento.update(evento)

    fun listarEvento(){
        viewModelScope.launch(Dispatchers.IO) {
             _eventos.postValue( repositoryEvento.listarEventos())
        }
    }

}