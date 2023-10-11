package id.rafilutfansyah.pokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.rafilutfansyah.pokedex.database.dao.PokemonDao
import id.rafilutfansyah.pokedex.model.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            INSTANCE?.let { return it }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_database",
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
