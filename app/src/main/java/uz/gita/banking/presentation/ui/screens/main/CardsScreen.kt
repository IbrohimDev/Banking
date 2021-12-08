package uz.gita.banking.presentation.ui.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import uz.gita.banking.R
import uz.gita.banking.databinding.ScreenCardsBinding
import uz.gita.banking.presentation.viewModels.impl.CardScreenViewModelImpl
import uz.gita.banking.presentation.viewModels.interfaces.CardScreenViewModel
import uz.gita.banking.utils.click
import uz.gita.banking.utils.scope

@AndroidEntryPoint
class CardsScreen:Fragment(R.layout.screen_cards) {

    private val binding by viewBinding(ScreenCardsBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel:CardScreenViewModel by viewModels<CardScreenViewModelImpl>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.addCardFlow.collect {
                navController.navigate(CardsScreenDirections.actionCardsScreenToAddCardScreen2())
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        cardAddBtn.click {
         viewModel.pressAddCard()
        }
        loadFlows()
    }

    private fun loadFlows() = binding.scope {

    }
}