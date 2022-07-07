package br.com.zup.marvelheroes.domain.usecase

import android.app.Application
import br.com.zup.marvelheroes.data.datasource.local.database.CharacterDatabase
import br.com.zup.marvelheroes.domain.model.Personagem
import br.com.zup.marvelheroes.domain.repository.CharacterRepository
import br.com.zup.marvelheroes.ui.viewstate.ViewState

class CharacterUseCase(application: Application) {
    private val characterDao = CharacterDatabase.getDatabase(application).characterDao()
    private val characterRepository = CharacterRepository(characterDao)

    suspend fun getAllCharacters(): ViewState<List<Personagem>> {
        try {
           val characters = characterRepository.getAllCharacters()
            return ViewState.Success(characters)
        } catch (e: Exception) {
            return ViewState.Error(Exception("Não foi possível carregar a lista."))
        }
    }

    suspend fun insertCharacter(char: Personagem) : ViewState<Personagem> {
        return try {
            characterRepository.insertCharacter(char)
            ViewState.Success(char)
        } catch (e: Exception) {
            ViewState.Error(Exception("Não foi possível inserir personagem."))
        }
    }
}