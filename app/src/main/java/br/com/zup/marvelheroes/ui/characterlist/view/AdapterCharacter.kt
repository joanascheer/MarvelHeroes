package br.com.zup.marvelheroes.ui.characterlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.marvelheroes.R
import br.com.zup.marvelheroes.domain.model.Character

class AdapterCharacter(
    private var characterList: MutableList<Character>,
    private val click: (char: Character) -> Unit,
) : RecyclerView.Adapter<AdapterCharacter.CharacterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemList = LayoutInflater.from(parent.context).inflate(R.layout.personagem_item, parent, false)
        val holder = CharacterViewHolder(itemList)
        return holder
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val char = characterList[position]
        holder.picture.setImageResource(characterList[position].characterPicture)
        holder.name.text = characterList[position].characterName
        holder.itemView.setOnClickListener {
            click(char)
        }
    }

    override fun getItemCount(): Int = characterList.size

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val picture = itemView.findViewById<ImageView>(R.id.ivPersonagem)
        val name = itemView.findViewById<TextView>(R.id.nomePersonagem)
    }

    fun refreshList (newList: MutableList<Character>){
        if (characterList.size == 0){
            characterList = newList
        }else{
            characterList.addAll(newList)
        }
        notifyDataSetChanged()
    }
}