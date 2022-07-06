package br.com.zup.marvelheroes.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.marvelheroes.domain.model.Character

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM characters ORDER BY nome ASC")
    fun getAllCharacters() : List<Character>

    @Query("SELECT * FROM characters WHERE nome =:charName")
    fun getCharName(charName: String) : Character

    @Query("SELECT * FROM characters WHERE nome =:charDescription")
    fun getCharDescription(charDescription: String) : Character

    @Query("SELECT * FROM characters WHERE nome =:charPicture")
    fun getCharPicture(charPicture: Int) : Character

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newChar(char: Character)
}