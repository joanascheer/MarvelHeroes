package br.com.zup.marvelheroes.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.marvelheroes.CHARACTER_KEY
import br.com.zup.marvelheroes.R
import br.com.zup.marvelheroes.databinding.FragmentCharacterDetailBinding
import br.com.zup.marvelheroes.domain.model.Personagem
import br.com.zup.marvelheroes.ui.home.view.MainActivity

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
        (activity as MainActivity).supportActionBar?.title = getString(R.string.character_detail_title_menu)

        getCharacterDetails()

    }

    private fun getCharacterDetails() {
        val char = arguments?.getParcelable<Personagem>(CHARACTER_KEY)

        char?.let {
            binding.ivDetalhePersonagem.setImageResource(it.characterPicture)
            binding.tvDetalheNomePersonagem.text = it.characterName
            binding.tvDetalheDescricaoPersonagem.text = it.characterDescripion
            (activity as MainActivity).supportActionBar?.title = it.characterName
        }

    }

}