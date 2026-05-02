package br.com.devandroid.registrodeeventosroom.presentation.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.devandroid.registrodeeventosroom.R
import br.com.devandroid.registrodeeventosroom.databinding.ActivityMainBinding
import br.com.devandroid.registrodeeventosroom.presentation.AdapterEvento
import br.com.devandroid.registrodeeventosroom.presentation.viewmodel.ViewModelEvento
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater) }
    private val viewModelEvento: ViewModelEvento by viewModels()
    private val adapterEventos = AdapterEvento()

    private var coroutineScope = CoroutineScope(Dispatchers.IO)


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModelEvento.listarEvento()

        binding.floatingActionButtonCadastro.setOnClickListener {
             startActivity(Intent(this, CadastroEventosActivity::class.java))
             overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        viewModelEvento.eventos.observe(this){ eventos ->
             adapterEventos.setLista(eventos)
        }

        configurarRecyclerView()

        adapterEventos.onclickDelete = { evento ->
               var resultadoOperacaio = ""

               coroutineScope.launch {
                 resultadoOperacaio = if(viewModelEvento.deletarEvento(evento) > 0){
                       "Evento deletado com sucesso!"

                  }else{
                       "Erro ao deletar o evento!"
                  }

                   withContext(Dispatchers.Main){
                       startActivity(Intent(this@MainActivity, MainActivity::class.java))
                       overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                       Toast.makeText(this@MainActivity, resultadoOperacaio, Toast.LENGTH_SHORT).show()
                   }

               }


        }

        adapterEventos.onclickEdit = { evento ->
            var resultadoOperacaio = ""

            coroutineScope.launch {
                resultadoOperacaio = if(viewModelEvento.deletarEvento(evento) > 0){
                    "Evento atualizado com sucesso!"
                }else{
                    "Erro ao atualizar o evento!"
                }
            }

            Toast.makeText(this, resultadoOperacaio, Toast.LENGTH_SHORT).show()
        }

    }

    fun configurarRecyclerView(){
          binding.rvEventos.adapter = adapterEventos
          binding.rvEventos.layoutManager = LinearLayoutManager(this)
          binding.rvEventos.setHasFixedSize(true)
    }


    override fun onStart() {
        super.onStart()
        viewModelEvento.listarEvento()
    }
}