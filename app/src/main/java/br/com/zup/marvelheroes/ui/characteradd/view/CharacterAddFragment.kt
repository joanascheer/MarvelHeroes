package br.com.zup.marvelheroes.ui.characteradd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.zup.marvelheroes.R
import br.com.zup.marvelheroes.databinding.FragmentCharacterAddBinding
import br.com.zup.marvelheroes.domain.model.Character
import br.com.zup.marvelheroes.ui.characteradd.viewmodel.CharacterAddViewModel
import br.com.zup.marvelheroes.ui.home.view.MainActivity
import br.com.zup.marvelheroes.ui.viewstate.ViewState

class CharacterAddFragment : Fragment() {
    private lateinit var binding: FragmentCharacterAddBinding
    private val viewModel: CharacterAddViewModel by lazy {
        ViewModelProvider(this)[CharacterAddViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            viewModel.insertCharacter(
                Character(
                    //falta a imagem
                    characterName = binding.etCharacterName.text.toString(),
                    characterDescripion = binding.etCharacterDescription.text.toString()
                )
            )
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.characterAddState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(context, "Filme cadastrado com sucesso!", Toast.LENGTH_LONG)
                        .show()
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

}