package br.com.zup.marvelheroes.ui.characteradd.viewmodel

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

class CharacterAddViewModel(application: Application) : AndroidViewModel(application) {
    private val personagemUseCase = PersonagemUseCase(application)
    val personagemAddState = MutableLiveData<ViewState<Personagem>>()

    fun insertCharacter(char: Personagem) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    personagemUseCase.insertCharacter(char)
                }
                personagemAddState.value = response
            } catch (ex: Exception) {
                personagemAddState.value =
                    ViewState.Error(Throwable("Não foi possível inserir o filme!"))
            }
        }
    }

    fun verifyCharacter(
        name: String,
        description:String
    ) {
        if (name.isNotEmpty() && description.isNotEmpty()) {
            insertCharacter(
                Personagem(
                    characterName = name,
                    characterDescripion = description
                )
            )
        } else {
            personagemAddState.value = ViewState.Error(Throwable("Por favor, preencha os campos corretamente!"))
        }
    }
}