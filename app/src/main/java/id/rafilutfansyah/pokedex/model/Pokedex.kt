package id.rafilutfansyah.pokedex.model

import kotlinx.serialization.Serializable

@Serializable()
data class Pokedex(
    val next: String,
    val results: List<Pokemon>,
)