package br.com.zup.marvelheroes.domain.repository

import br.com.zup.marvelheroes.data.datasource.local.dao.PersonagemDAO
import br.com.zup.marvelheroes.domain.model.Personagem

class PersonagemRepository(private val personagemDAO: PersonagemDAO) {

    suspend fun getAllCharacters() : List<Personagem> = personagemDAO.getAllCharacters()

    suspend fun insertCharacter(personagem: Personagem) {
        personagemDAO.newChar(personagem)
    }
}