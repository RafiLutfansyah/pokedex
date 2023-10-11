package id.rafilutfansyah.pokedex.view.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import id.rafilutfansyah.pokedex.R
import id.rafilutfansyah.pokedex.model.Pokemon
import id.rafilutfansyah.pokedex.utils.capitalizeWords
import id.rafilutfansyah.pokedex.view.detail.DetailActivity

class PokemonAdapter(private val context: Context, private val dataSet: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cvPokemon: CardView
        val tvPokemonName: TextView

        init {
            cvPokemon = view.findViewById(R.id.cvPokemon)
            tvPokemonName = view.findViewById(R.id.tvPokemonName)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_pokemon, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cardColors = listOf("#FFD3E8", "#E8D7FF", "#DAC4F7", "#FFD7D5").shuffled()
        viewHolder.cvPokemon.setCardBackgroundColor(Color.parseColor(cardColors[0]))

        viewHolder.tvPokemonName.text = dataSet[position].name.capitalizeWords()

        viewHolder.cvPokemon.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("pokemon_name", dataSet[position].name)
            intent.putExtra("pokemon_url", dataSet[position].url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}
