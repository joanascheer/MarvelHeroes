package br.com.zup.marvelheroes.data.datasource.local

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun converterFromCharacter(value: String): Character? {
        return Gson().fromJson(value, Character::class.java)
    }

    @TypeConverter
    fun converterToJson(char: Character): String {
        return Gson().toJson(char)
    }
}