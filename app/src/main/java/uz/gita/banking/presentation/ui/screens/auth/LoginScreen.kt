package uz.gita.banking.presentation.ui.screens.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.banking.R
import uz.gita.banking.data.remote.request.auth.RequestAuthLogin
import uz.gita.banking.databinding.ScreenLoginBinding
import uz.gita.banking.presentation.viewModels.impl.LoginScreenViewModelImpl
import uz.gita.banking.presentation.viewModels.interfaces.LoginScreenViewModel
import uz.gita.banking.utils.*

@AndroidEntryPoint
class LoginScreen:Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel:LoginScreenViewModel by viewModels<LoginScreenViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.registerScreenLiveData.collect {
                navController.navigate(LoginScreenDirections.actionLoginScreenToRegisterScreen())
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.successLiveData.collect {
                navController.navigate(LoginScreenDirections.actionLoginScreenToAuthVerifyScreen())
            }
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        loginBtn.click {
            val unMaskPhone = phoneInput.rawText
            unMaskPhone?.let {
                    it1 -> timber(it1)
                viewModel.userLogin(RequestAuthLogin("+998$unMaskPhone",passwordInput.text.toString()))
            }

        }
        combine(
            phoneInput.textChanges()
                .map { it.length == 19 },
            passwordInput.textChanges()
                .map { it.length in 7..20 },

            transform = {phone,password -> phone && password}
        ).onEach {
            loginBtn.isEnabled = true
        }.launchIn(lifecycleScope)



        createAcccountTxt.click {
            viewModel.openRegisterScreen()
        }



     loadFlows()
    }

    private fun loadFlows() = binding.scope{

        lifecycleScope.launchWhenStarted {
            viewModel.disableLoginLiveData.collect {
                binding.loginBtn.isEnabled  = false
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.enableLoginLiveData.collect {
                binding.loginBtn.isEnabled  = true
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.errorLivaData.collect {
                showToast(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.notInternetLiveData.collect {
                showToast(resources.getString(R.string.error))
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.progressLiveData.collect {
                when(it){
                    true -> binding.progress.visible()
                    else -> binding.progress.gone()
                }
            }
        }
    }



}