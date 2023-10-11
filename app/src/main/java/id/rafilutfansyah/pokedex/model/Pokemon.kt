package id.rafilutfansyah.pokedex.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Pokemon(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
)