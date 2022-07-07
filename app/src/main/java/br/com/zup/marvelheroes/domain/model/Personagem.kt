package br.com.zup.marvelheroes.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "characters")
class Personagem(

    @ColumnInfo(name = "foto")
    var characterPicture: Int = 0,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "nome")
    var characterName: String = "",
    @ColumnInfo(name = "descricao")
    var characterDescripion: String = "",

) : Parcelable