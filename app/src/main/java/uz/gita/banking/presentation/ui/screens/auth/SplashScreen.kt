package uz.gita.banking.presentation.ui.screens.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import uz.gita.banking.R
import uz.gita.banking.databinding.ScreenSplashBinding
import uz.gita.banking.presentation.viewModels.impl.SplashScreenViewModelImpl
import uz.gita.banking.presentation.viewModels.interfaces.SplashScreenViewModel
import uz.gita.banking.utils.scope

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {

    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()
    private val navController by lazy { findNavController() }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        lifecycleScope.launchWhenStarted {
            viewModel.openLoginScreenLiveData.collect {
                navController.navigate(SplashScreenDirections.actionSplashScreenToLoginScreen())
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.openMainScreenLiveData.collect {
                navController.navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.openPinScreenLiveData.collect {
                navController.navigate(SplashScreenDirections.actionSplashScreenToPinScreen())
            }
        }

    }



}