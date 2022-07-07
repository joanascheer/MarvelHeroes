package br.com.zup.marvelheroes.domain.repository

import br.com.zup.marvelheroes.data.datasource.local.dao.CharacterDAO
import br.com.zup.marvelheroes.domain.model.Character

class CharacterRepository(private val characterDAO: CharacterDAO) {

    suspend fun getAllCharacters() : List<Character> = characterDAO.getAllCharacters()

    suspend fun insertCharacter(character: Character) {
        characterDAO.newChar(character)
    }
}