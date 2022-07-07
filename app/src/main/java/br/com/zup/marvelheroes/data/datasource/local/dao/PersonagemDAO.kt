package br.com.zup.marvelheroes.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.marvelheroes.domain.model.Personagem

@Dao
interface PersonagemDAO {
    @Query("SELECT * FROM characters ORDER BY nome ASC")
    fun getAllCharacters() : List<Personagem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newChar(char: Personagem)
}

