package id.rafilutfansyah.pokedex.view.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.rafilutfansyah.pokedex.R
import id.rafilutfansyah.pokedex.utils.capitalizeWords

class AbilityAdapter(private val context: Context, private val dataSet: List<String>) :
    RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPokemonName: TextView

        init {
            tvPokemonName = view.findViewById(R.id.tvAbilityName)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_ability, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvPokemonName.text = dataSet[position].capitalizeWords()
    }

    override fun getItemCount() = dataSet.size
}
