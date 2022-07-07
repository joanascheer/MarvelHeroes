package br.com.zup.marvelheroes.ui.characterlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.marvelheroes.domain.model.Personagem
import br.com.zup.marvelheroes.domain.usecase.CharacterUseCase
import br.com.zup.marvelheroes.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val characterUseCase = CharacterUseCase(application)
    val personagemListState = MutableLiveData<ViewState<List<Personagem>>>()

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    characterUseCase.getAllCharacters()
                }
                personagemListState.value = response
            } catch (e: Exception) {
                personagemListState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a lista."))
            }
        }
    }
}