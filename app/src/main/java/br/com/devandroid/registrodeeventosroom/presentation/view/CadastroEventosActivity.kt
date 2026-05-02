package br.com.devandroid.registrodeeventosroom.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devandroid.registrodeeventosroom.R
import br.com.devandroid.registrodeeventosroom.data.model.Evento
import br.com.devandroid.registrodeeventosroom.databinding.ActivityCadastroEventosBinding
import br.com.devandroid.registrodeeventosroom.presentation.viewmodel.ViewModelEvento
import br.com.devandroid.registrodeeventosroom.util.DataAtual
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CadastroEventosActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCadastroEventosBinding.inflate(layoutInflater) }

    private val viewModelEvento: ViewModelEvento by viewModels()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var resposta: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         binding.btVoltar.setOnClickListener {
             startActivity(Intent(this, MainActivity::class.java))
             overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
         }

         binding.btCadastro.setOnClickListener {

                 if(validarCampos()){
                      val titutlo = binding.editTitulo.text.toString()
                      val descricao = binding.editDescricao.text.toString()
                      val data = DataAtual.getDataHoraAtual()

                      val evento = Evento(null, titutlo, data, descricao)

                      coroutineScope.launch {
                            val retorno = viewModelEvento.salvarEvento(evento)
                            resposta = if(retorno > 0){
                                "Evento cadastrado com sucesso!"
                            }else{
                                "Evento não cadastrado!"
                            }

                          withContext(Dispatchers.Main){
                              Toast.makeText(this@CadastroEventosActivity, resposta, Toast.LENGTH_SHORT).show()
                          }
                      }


                 }

         }

    }

    fun validarCampos(): Boolean{
          if(binding.editTitulo.text!!.isEmpty()){
              binding.editTitulo.error = "Campo obrigatório"
              return false
          }

          if(binding.editDescricao.text!!.isEmpty()){
              binding.editDescricao.error = "Campo obrigatório"
              return false
          }

          return true
    }
}