package br.com.zup.marvelheroes.data.datasource.local.database

import android.content.Context
import androidx.room.*
import br.com.zup.marvelheroes.data.datasource.local.Converters
import br.com.zup.marvelheroes.data.datasource.local.dao.CharacterDAO
import br.com.zup.marvelheroes.domain.model.Personagem

@Database(entities = [Personagem::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao() : CharacterDAO

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null
        fun getDatabase(context: Context): CharacterDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    "character_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

