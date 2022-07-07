package br.com.zup.marvelheroes.ui.characteradd.viewmodel

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

class CharacterAddViewModel(application: Application) : AndroidViewModel(application) {
    private val charUseCase = CharacterUseCase(application)
    val personagemAddState = MutableLiveData<ViewState<Personagem>>()

    fun insertCharacter(char: Personagem) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    charUseCase.insertCharacter(char)
                }
                personagemAddState.value = response
            } catch (ex: Exception) {
                personagemAddState.value =
                    ViewState.Error(Throwable("Não foi possível inserir o filme!"))
            }
        }
    }
}