package uz.gita.banking.presentation.ui.screens.auth

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
import uz.gita.banking.data.enum.PinStatus
import uz.gita.banking.databinding.ScreenCreatePinBinding
import uz.gita.banking.presentation.viewModels.impl.CreatePinViewModelImpl
import uz.gita.banking.utils.click
import uz.gita.banking.utils.scope

@AndroidEntryPoint
class CreatePinScreen : Fragment(R.layout.screen_create_pin) {

    private val binding by viewBinding(ScreenCreatePinBinding::bind)
    private val viewModel by viewModels<CreatePinViewModelImpl>()
    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted{
            viewModel.pinScreen.collect {
                navController.navigate(CreatePinScreenDirections.actionGlobalPinScreen())
            }
        }
        lifecycleScope.launchWhenStarted{
            viewModel.mainScreen.collect {
                navController.navigate(CreatePinScreenDirections.actionGlobalMainScreen())
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        skipTxt.click {
            viewModel.apply {
                setPinStatus(PinStatus.WithoutPin)
                setMainScreen()
            }
        }
        pinLockView.addPatternLockListener(object : PatternLockViewListener {
            override fun onStarted() {

            }

            override fun onProgress(progressPattern: MutableList<PatternLockView.Dot>?) {

            }

            override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {
                val pin = PatternLockUtils.patternToString(pinLockView, pattern)
                if (pin.length > 3) {
                    viewModel?.apply {
                        setPinCode(pin)
                        pressPinScreen()
                    }
                }
            }

            override fun onCleared() {

            }
        })


       loadFlows()
    }

    private fun loadFlows() = binding.scope{

    }
}