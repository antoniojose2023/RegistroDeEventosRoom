package br.com.devandroid.registrodeeventosroom.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.devandroid.registrodeeventosroom.data.model.Evento
import br.com.devandroid.registrodeeventosroom.databinding.ItemEventoBinding

class AdapterEvento(val onclickDelete: (Evento) -> Unit, val onclickEdit: (Evento) -> Unit): RecyclerView.Adapter<AdapterEvento.EventoViewHolder>() {

    private var eventos = mutableListOf<Evento>()

    @SuppressLint("NotifyDataSetChanged")
    fun setLista(lista: List<Evento>){
          eventos.addAll(lista)
          notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
              val inflater = LayoutInflater.from(parent.context)
              val binding = ItemEventoBinding.inflate(  inflater, parent, false )
              return EventoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: EventoViewHolder,position: Int) {
             val evento = eventos[position]
             holder.holder(evento)

    }

    override fun getItemCount() = eventos.size

    inner class EventoViewHolder(val binding: ItemEventoBinding): RecyclerView.ViewHolder(binding.root){
        fun holder(item: Evento){
               with( binding ){
                     tvTitulo.text = item.titulo
                     tvDescricao.text = item.conteudo
                     tvData.text = item.data
               }

              binding.cardItemEvento.setOnClickListener{

              }

              binding.imageButtonDelete.setOnClickListener {
                       onclickDelete(item)
              }

              binding.imageButtonUpdate.setOnClickListener {
                      onclickEdit(item)
              }

        }




    }

}