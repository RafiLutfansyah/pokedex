package id.rafilutfansyah.pokedex.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.serialization.responseObject
import com.github.kittinunf.result.Result
import id.rafilutfansyah.pokedex.databinding.ActivityDetailBinding
import id.rafilutfansyah.pokedex.model.PokemonDetail
import id.rafilutfansyah.pokedex.utils.VerticalSpaceItemDecoration
import id.rafilutfansyah.pokedex.utils.capitalizeWords
import id.rafilutfansyah.pokedex.utils.toDp
import kotlinx.serialization.json.Json

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        binding.rvAbility.apply {
            addItemDecoration(VerticalSpaceItemDecoration(16.toDp(this@DetailActivity)))
            layoutManager = LinearLayoutManager(this@DetailActivity)
        }

        intent.getStringExtra("pokemon_name")?.let {
            binding.tvPokemonName.text = it.capitalizeWords()
        }

        intent.getStringExtra("pokemon_url")?.let {
            Fuel.get(it).responseObject<PokemonDetail>(json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            }) { _, _, result ->
                when (result) {
                    is Result.Success -> {
                        Glide.with(this).load(result.get().sprites.frontDefault).into(binding.imgPokemon)

                        binding.rvAbility.adapter = AbilityAdapter(this@DetailActivity, result.get().abilities.map { abilities ->
                            abilities.ability.name
                        })
                    }

                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                }
            }.join()
        }
    }
}