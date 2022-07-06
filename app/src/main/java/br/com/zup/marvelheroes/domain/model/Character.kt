package br.com.zup.marvelheroes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Character(
    val characterPicture: Int,
    val characterName: String,
    val characterDescripion: String,
) : Parcelable {
    fun getName() = characterName
    fun getDescription() = characterDescripion
    fun getPicture() = characterPicture
}