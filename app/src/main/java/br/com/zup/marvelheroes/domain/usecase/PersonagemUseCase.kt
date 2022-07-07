package br.com.zup.marvelheroes.domain.usecase

import android.app.Application
import br.com.zup.marvelheroes.ERROR_INSERT_CHARACTER
import br.com.zup.marvelheroes.ERROR_LOAD_LIST
import br.com.zup.marvelheroes.data.datasource.local.database.PersonagemDatabase
import br.com.zup.marvelheroes.domain.model.Personagem
import br.com.zup.marvelheroes.domain.repository.PersonagemRepository
import br.com.zup.marvelheroes.ui.viewstate.ViewState

class PersonagemUseCase(application: Application) {
    private val characterDao = PersonagemDatabase.getDatabase(application).characterDao()
    private val personagemRepository = PersonagemRepository(characterDao)

    suspend fun getAllCharacters(): ViewState<List<Personagem>> {
        try {
           val characters = personagemRepository.getAllCharacters()
            return ViewState.Success(characters)
        } catch (e: Exception) {
            return ViewState.Error(Exception(ERROR_LOAD_LIST))
        }
    }

    suspend fun insertCharacter(char: Personagem) : ViewState<Personagem> {
        return try {
            personagemRepository.insertCharacter(char)
            ViewState.Success(char)
        } catch (e: Exception) {
            ViewState.Error(Exception(ERROR_INSERT_CHARACTER))
        }
    }
}