package br.com.zup.marvelheroes.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "characters")
class Character(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_character")
    val characterId: Long = 1,
    @ColumnInfo(name = "foto")
    val characterPicture: Int = 0,
    @ColumnInfo(name = "nome")
    val characterName: String = "",
    @ColumnInfo(name = "descricao")
    val characterDescripion: String = "",

) : Parcelable {
    fun getName() = characterName
    fun getDescription() = characterDescripion
    fun getPicture() = characterPicture
}