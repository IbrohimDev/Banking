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
import uz.gita.banking.data.remote.request.auth.RequestAuthRegister
import uz.gita.banking.databinding.ScreenRegisterBinding
import uz.gita.banking.presentation.viewModels.impl.RegisterScreenViewModelImpl
import uz.gita.banking.presentation.viewModels.interfaces.RegisterScreenViewModel
import uz.gita.banking.utils.*

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {
    private val binding by viewBinding(ScreenRegisterBinding::bind)
    private val viewModel: RegisterScreenViewModel by viewModels<RegisterScreenViewModelImpl>()
    private val navController by lazy { findNavController() }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.successLiveData.collect {
                navController.navigate(RegisterScreenDirections.actionRegisterScreenToAuthVerifyScreen())
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.loginScreenLiveData.collect {
                navController.navigate(RegisterScreenDirections.actionRegisterScreenToLoginScreen())
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        combine(
            firstnameInput.textChanges()
                .map { it.length in 3..20 },
            lastnameInput.textChanges()
                .map {it.length in 3..20  },
            phoneInput.textChanges()
                .map { it.length == 19 },
            passwordInput.textChanges()
                .map { it.length in 7..20  },
           passwordRepeatInput.textChanges().
               map {   it.length in 7..20 && it.toString() == passwordInput.text.toString() },

            transform = {firstName,lastName,phone,password,repeatPassword -> firstName && lastName && phone && password && repeatPassword}
        ).onEach {
            binding.singupBtn.isEnabled = true
        }.launchIn(lifecycleScope)


        binding.singupBtn.click {
            val unMaskPhone = phoneInput.rawText
            unMaskPhone?.let { it1 -> timber(it1) }
            viewModel.userRegister(RequestAuthRegister(
                firstnameInput.text.toString().trim(),
                lastnameInput.text.toString().trim(),
                passwordInput.text.toString(),
                "+998$unMaskPhone",
                0
            ))
        }

      createAcccountTxt.click {
          viewModel.pressLogin()
      }
       loadFlows()
    }

    private fun loadFlows() = binding.scope {
        lifecycleScope.launchWhenStarted {
            viewModel.disableSignUpLiveData.collect {
                binding.singupBtn.isEnabled = false
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
                when (it) {
                    true -> binding.progress.visible()
                    else -> binding.progress.gone()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.enableSignUpLiveData.collect {
                binding.singupBtn.isEnabled = true
            }
        }

    }
}