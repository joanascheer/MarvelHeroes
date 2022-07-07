package br.com.zup.marvelheroes.data.datasource.local

import androidx.room.TypeConverter
import br.com.zup.marvelheroes.domain.model.Personagem
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun converterFromPersonagem(value: String): Personagem? {
        return Gson().fromJson(value, Personagem::class.java)
    }

    @TypeConverter
    fun converterToJson(char: Personagem): String {
        return Gson().toJson(char)
    }
}