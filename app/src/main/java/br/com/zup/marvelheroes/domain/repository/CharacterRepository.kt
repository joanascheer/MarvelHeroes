package br.com.zup.marvelheroes.domain.repository

import br.com.zup.marvelheroes.data.datasource.local.dao.CharacterDAO
import br.com.zup.marvelheroes.domain.model.Personagem

class CharacterRepository(private val characterDAO: CharacterDAO) {

    suspend fun getAllCharacters() : List<Personagem> = characterDAO.getAllCharacters()

    suspend fun insertCharacter(personagem: Personagem) {
        characterDAO.newChar(personagem)
    }
}