package uz.gita.banking.presentation.ui.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.banking.R
import uz.gita.banking.data.remote.request.card.RequestAddCard
import uz.gita.banking.databinding.ScreenAddCardBinding
import uz.gita.banking.presentation.viewModels.impl.AddCardViewModelImpl
import uz.gita.banking.presentation.viewModels.interfaces.AddCardViewModel
import uz.gita.banking.utils.*
@AndroidEntryPoint
class AddCardScreen :Fragment(R.layout.screen_add_card){

    private val binding by viewBinding(ScreenAddCardBinding::bind)
    private val viewModel:AddCardViewModel by viewModels<AddCardViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.successFlow.collect {
                navController.popBackStack()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.backBtnFlow.collect {
                navController.popBackStack()
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {


        combine(
            cardNumberInput.textChanges()
                .map {
                    it.length == 19
                },
           expireDateInput.textChanges()
               .map {
                   it.length == 5
               },
            cardNameInput.textChanges()
                .map {
                    it.length in 3..20
                },
            transform = {cardNumber,expireDate,cardName -> cardNumber && expireDate && cardName}
        ).onEach{
           addCardBtn.isEnabled = it
        }.launchIn(lifecycleScope)

        cardNumberInput.textChanges()
            .onEach {
                numberCard.text = it
            }.launchIn(lifecycleScope)
        expireDateInput.textChanges()
            .onEach {
                expireCard.text = it
            }.launchIn(lifecycleScope)
        cardNameInput.textChanges()
            .onEach {
                titleCard.text = it
            }.launchIn(lifecycleScope)
      loadViews()
      loadFlows()
    }

    private fun loadViews() = binding.scope {

             lifecycleScope.launch {
                 addCardBtn.clicks {
                     val pan = cardNumberInput.rawText
                     val exp = expireDateInput.rawText

                     viewModel.addCard(
                         RequestAddCard(
                             pan,expireDateInput.text.toString(),cardNameInput.text.toString().trim()
                         )
                     )
                 }

             }

        backAddBtn.clicks()
            .debounce(100)
            .onEach { viewModel.pressBack() }
            .launchIn(lifecycleScope)


    }

    private fun loadFlows() = binding.scope {

        lifecycleScope.launchWhenStarted {
            viewModel.disableAddFlow.collect {
                addCardBtn.isEnabled = false
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.enableAddFlow.collect {
                addCardBtn.isEnabled = true
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.errorLivaData.collect {
                showToast(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.processFlow.collect {
                when(it){
                    true -> progress.visible()
                    false -> progress.gone()
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.notInternetFlow.collect {
                showToast(getString(R.string.error))
            }
        }

    }
}