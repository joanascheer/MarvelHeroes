package br.com.zup.marvelheroes.ui.characteradd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.marvelheroes.R
import br.com.zup.marvelheroes.databinding.FragmentCharacterAddBinding
import br.com.zup.marvelheroes.domain.model.Personagem
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
    ): View {
        binding = FragmentCharacterAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.main_title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            viewModel.verifyCharacter(
                name = binding.etCharacterName.text.toString(),
                description = binding.etCharacterDescription.text.toString(),
            )
            goToCharactersList()
        }
        initObserver()
    }

    private fun goToCharactersList() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_characterAddFragment_to_characterListFragment)
    }

    private fun initObserver() {
        viewModel.personagemAddState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    cleanEditText()
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

    private fun cleanEditText() {
        binding.etCharacterName.text.clear()
        binding.etCharacterDescription.text.clear()
        binding.etImageHere.text.clear()
    }

}