package br.com.zup.marvelheroes.ui.characterlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.marvelheroes.R
import br.com.zup.marvelheroes.domain.model.Personagem

class AdapterCharacter(
    private var personagemList: MutableList<Personagem>,
    private val click: (char: Personagem) -> Unit,
) : RecyclerView.Adapter<AdapterCharacter.CharacterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemList =
            LayoutInflater.from(parent.context).inflate(R.layout.personagem_item, parent, false)
        val holder = CharacterViewHolder(itemList)
        return holder
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val char = personagemList[position]
        holder.picture.setImageResource(personagemList[position].characterPicture)
        holder.name.text = personagemList[position].characterName
        holder.itemView.setOnClickListener {
            click(char)
        }
    }

    override fun getItemCount(): Int = personagemList.size

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val picture = itemView.findViewById<ImageView>(R.id.ivPersonagem)
        val name = itemView.findViewById<TextView>(R.id.nomePersonagem)
    }


    fun refreshList(newList: List<Personagem>) {

        personagemList = newList as MutableList<Personagem>

        notifyDataSetChanged()
    }
}