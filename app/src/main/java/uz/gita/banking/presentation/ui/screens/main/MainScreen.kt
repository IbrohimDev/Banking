package uz.gita.banking.presentation.ui.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect

import uz.gita.banking.R
import uz.gita.banking.data.enum.BottomPageEnum
import uz.gita.banking.databinding.ScreenMainBinding
import uz.gita.banking.presentation.ui.adapters.BottomAttachPagerAdapter
import uz.gita.banking.presentation.viewModels.impl.MainScreenViewModelImpl
import uz.gita.banking.presentation.viewModels.interfaces.MainScreenViewModel
import uz.gita.banking.utils.click
import uz.gita.banking.utils.scope


class MainScreen : Fragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)

    private val navController by lazy { findNavController() }
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.cardScreenFlow.collect {
                navController.navigate(MainScreenDirections.actionMainScreenToCardsScreen())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
      val bottomAttachPagerAdapter =   BottomAttachPagerAdapter(childFragmentManager, lifecycle)
        pager.adapter = bottomAttachPagerAdapter
        pager.isUserInputEnabled = false

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main_menu -> viewModel.selectPosPage(BottomPageEnum.Main)
                R.id.transfer_menu -> viewModel.selectPosPage(BottomPageEnum.TRANSFER)
                R.id.payment_menu -> viewModel.selectPosPage(BottomPageEnum.PAYMENT)
                R.id.service_menu -> viewModel.selectPosPage(BottomPageEnum.SERVICES)
                R.id.income_outcome_menu -> viewModel.selectPosPage(BottomPageEnum.HISTORY)
            }
            return@setOnItemSelectedListener true
        }
        cardItem.click {
           viewModel.pressCard()
        }

        loadFlow()
    }

    private fun loadFlow() = binding.scope {
        lifecycleScope.launchWhenStarted {
            viewModel.openSelectPageByBottom.collect {
                pager.currentItem = it
            }
        }
    }
}