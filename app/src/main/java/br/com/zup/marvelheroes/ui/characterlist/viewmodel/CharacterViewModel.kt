package br.com.zup.marvelheroes.ui.characterlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.marvelheroes.domain.model.Personagem
import br.com.zup.marvelheroes.domain.model.SingleLiveEvent
import br.com.zup.marvelheroes.domain.usecase.PersonagemUseCase
import br.com.zup.marvelheroes.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val personagemUseCase = PersonagemUseCase(application)
    val personagemListState = SingleLiveEvent<ViewState<List<Personagem>>>()

    fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    personagemUseCase.getAllCharacters()
                }
                personagemListState.value = response
            } catch (e: Exception) {
                personagemListState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a lista."))
            }
        }
    }
}