package id.rafilutfansyah.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.rafilutfansyah.pokedex.model.Pokemon

@Dao
interface PokemonDao {
    @Query("SELECT * FROM Pokemon WHERE LOWER(TRIM(name)) LIKE '%' ||  LOWER(TRIM(:name)) || '%' " +
            "ORDER BY CASE WHEN :order = 'ASC' THEN name END, CASE WHEN :order = 'DESC' THEN name END DESC")
    fun findByName(name: String, order: String): List<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemons: List<Pokemon>)
}