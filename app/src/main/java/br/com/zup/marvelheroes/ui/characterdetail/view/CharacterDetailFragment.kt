package br.com.zup.marvelheroes.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.marvelheroes.CHARACTER_KEY
import br.com.zup.marvelheroes.R
import br.com.zup.marvelheroes.databinding.FragmentCharacterDetailBinding
import br.com.zup.marvelheroes.domain.model.Character

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCharacterDetails()

    }

    private fun getCharacterDetails() {
        val char = arguments?.getParcelable<Character>(CHARACTER_KEY)

        if (char != null) {
            binding.ivDetalhePersonagem.setImageResource(char.getPicture())
            binding.tvDetalheNomePersonagem.text = char.getName()
            binding.tvDetalheDescricaoPersonagem.text = char.getDescription()
        }

    }

}