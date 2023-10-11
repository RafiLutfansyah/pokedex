@file:OptIn(DelicateCoroutinesApi::class)

package id.rafilutfansyah.pokedex.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.serialization.responseObject
import com.github.kittinunf.result.Result
import id.rafilutfansyah.pokedex.database.AppDatabase
import id.rafilutfansyah.pokedex.databinding.ActivityMainBinding
import id.rafilutfansyah.pokedex.model.Pokedex
import id.rafilutfansyah.pokedex.utils.VerticalSpaceItemDecoration
import id.rafilutfansyah.pokedex.utils.toDp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase
    private var debounceJob: Job? = null
    private var name = ""
    private var order = "ASC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        database = AppDatabase.getInstance(this)

        binding.rvPokedex.apply {
            addItemDecoration(VerticalSpaceItemDecoration(16.toDp(this@MainActivity)))
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.etSearch.addTextChangedListener { s ->
            debounceJob?.cancel()
            debounceJob = GlobalScope.launch {
                delay(500L)
                name = s.toString()
                getLocalPokemons()
            }
        }

        binding.btnSort.setOnClickListener {
            order = if (order == "ASC") "DESC" else "ASC"
            binding.btnSort.text = order
            getLocalPokemons()
        }

        Fuel.get("https://pokeapi.co/api/v2/pokemon").responseObject<Pokedex>(json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }) { _, _, result ->
            when (result) {
                is Result.Success -> {
                    GlobalScope.launch {
                        database.pokemonDao().insertAll(result.get().results)
                        getLocalPokemons()
                    }
                }

                is Result.Failure -> {
                    val ex = result.getException()
                    println(ex)
                }
            }
        }.join()
    }

    private fun getLocalPokemons() {
        GlobalScope.launch {
            val result = database.pokemonDao().findByName(name, order)

            launch(Dispatchers.Main) {
                binding.rvPokedex.adapter = PokemonAdapter(this@MainActivity, result)
            }
        }
    }
}