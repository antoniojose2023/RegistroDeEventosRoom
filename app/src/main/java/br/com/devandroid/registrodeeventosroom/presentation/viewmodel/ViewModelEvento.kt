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

    fun salvarEvento(evento: Evento): Long {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryEvento.salvar(evento)
        }
    }

    fun deletarEvento(evento: Evento): Int{
        viewModelScope.launch(Dispatchers.IO) {
              repositoryEvento.delete(evento)
        }
    }

    fun atualizarEvento(evento: Evento): Int{
          viewModelScope.launch(Dispatchers.IO) {
              repositoryEvento.update( evento )
          }
    }


    fun listarEvento(){
        viewModelScope.launch(Dispatchers.IO) {
             _eventos.value = repositoryEvento.listarEventos()
        }
    }

}