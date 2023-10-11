package id.rafilutfansyah.pokedex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(
    val abilities: List<Abilities>,
    val sprites: Sprites,
)

@Serializable
data class Abilities(
    val ability: Ability,
)

@Serializable
data class Ability(
    val name: String,
)

@Serializable
data class Sprites(
    @SerialName("front_default") val frontDefault: String,
)