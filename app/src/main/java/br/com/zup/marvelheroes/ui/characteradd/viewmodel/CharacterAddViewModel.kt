package br.com.zup.marvelheroes.ui.characteradd.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.marvelheroes.domain.model.Character
import br.com.zup.marvelheroes.domain.usecase.CharacterUseCase
import br.com.zup.marvelheroes.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterAddViewModel(application: Application) : AndroidViewModel(application) {
    private val charUseCase = CharacterUseCase(application)
    val characterAddState = MutableLiveData<ViewState<Character>>()

    fun insertCharacter(char: Character) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    charUseCase.insertCharacter(char)
                }
                characterAddState.value = response
            } catch (ex: Exception) {
                characterAddState.value =
                    ViewState.Error(Throwable("Não foi possível inserir o filme!"))
            }
        }
    }
}