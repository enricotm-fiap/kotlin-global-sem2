package com.example.global

import DicaAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import com.example.global.model.Dica
import com.example.global.viewmodel.DicaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var dicaAdapter: DicaAdapter
    private val dicaViewModel: DicaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        dicaAdapter = DicaAdapter(emptyList()) { dica ->
            Toast.makeText(this, dica.descricao, Toast.LENGTH_SHORT).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = dicaAdapter

        dicaViewModel.allDicas.observe(this, Observer { dicas ->
            dicas?.let { dicaAdapter.updateData(it) }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = dicaViewModel.allDicas.value?.filter {
                    it.titulo.contains(newText ?: "", ignoreCase = true) ||
                            it.descricao.contains(newText ?: "", ignoreCase = true)
                }
                dicaAdapter.updateData(filteredList ?: emptyList())
                return true
            }
        })

        // Exemplo de inserção de nova dica
        CoroutineScope(Dispatchers.IO).launch {
            val novaDica = Dica(titulo = "Economize água", descricao = "Feche a torneira enquanto escova os dentes.")
            dicaViewModel.insert(novaDica)
        }
    }
}


