package br.com.zup.marvelheroes.data.datasource.local.database

import android.content.Context
import androidx.room.*
import br.com.zup.marvelheroes.data.datasource.local.Converters
import br.com.zup.marvelheroes.data.datasource.local.dao.PersonagemDAO
import br.com.zup.marvelheroes.domain.model.Personagem

@Database(entities = [Personagem::class], version = 1)
@TypeConverters(Converters::class)
abstract class PersonagemDatabase: RoomDatabase() {
    abstract fun characterDao() : PersonagemDAO

    companion object {
        @Volatile
        private var INSTANCE: PersonagemDatabase? = null
        fun getDatabase(context: Context): PersonagemDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonagemDatabase::class.java,
                    "character_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

