package uz.gita.banking.presentation.ui.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

import uz.gita.banking.R
import uz.gita.banking.databinding.ScreenPinBinding
import uz.gita.banking.presentation.viewModels.impl.PinViewModelImpImpl
import uz.gita.banking.presentation.viewModels.interfaces.PinViewModel
import uz.gita.banking.utils.scope
import uz.gita.banking.utils.showToast

@AndroidEntryPoint
class PinScreen:Fragment(R.layout.screen_pin) {

    private val binding by viewBinding(ScreenPinBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel:PinViewModel by viewModels<PinViewModelImpImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.mainScreen.collect {
                navController.navigate(PinScreenDirections.actionPinScreenToMainScreen())
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        pinLockView.addPatternLockListener(object:PatternLockViewListener{

            override fun onStarted() {

            }

            override fun onProgress(progressPattern: MutableList<PatternLockView.Dot>?) {

            }

            override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {
                val pin = PatternLockUtils.patternToString(pinLockView, pattern)
                if(pin.length > 3){
                   viewModel.checkPin(pin!!)
                }
            }

            override fun onCleared() {

            }


        })
        lifecycleScope.launchWhenStarted {
            viewModel.errorFlow.collect {
                showToast(getString(R.string.wrong))
            }
        }

    }

}