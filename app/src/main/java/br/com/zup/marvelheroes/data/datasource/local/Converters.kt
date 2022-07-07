package br.com.zup.marvelheroes.data.datasource.local

import com.google.gson.Gson

class Converters {
    fun converterFromCharacter(value: String): Character? {
        return Gson().fromJson(value, Character::class.java)
    }
    fun converterToJson(char: Character): String {
        return Gson().toJson(char)
    }
}