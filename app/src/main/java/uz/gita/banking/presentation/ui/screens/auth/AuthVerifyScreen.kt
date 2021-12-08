package uz.gita.banking.presentation.ui.screens.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.uzairiqbal.circulartimerview.CircularTimerListener
import com.uzairiqbal.circulartimerview.TimeFormatEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.banking.R
import uz.gita.banking.databinding.ScreenVerifyBinding
import uz.gita.banking.presentation.viewModels.impl.AuthVerifyScreenViewModelImpl
import uz.gita.banking.presentation.viewModels.interfaces.AuthVerifyScreenViewModel
import uz.gita.banking.utils.*
import kotlin.math.ceil

@AndroidEntryPoint
class AuthVerifyScreen : Fragment(R.layout.screen_verify) {

    private val binding by viewBinding(ScreenVerifyBinding::bind)
    private val viewModel: AuthVerifyScreenViewModel by viewModels<AuthVerifyScreenViewModelImpl>()
    private val navController by lazy { findNavController() }

    private var isVerify = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.backLiveData.collect {
                navController.popBackStack()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.backLiveData.collect {
                navController.popBackStack()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.successLiveData.collect {
                navController.navigate(AuthVerifyScreenDirections.actionAuthVerifyScreenToCreatePinScreen())
            }
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {


        combine(
           verifyInput.textChanges()
               .map { it.length == 6 },

            transform = {verify -> verify}
        ).onEach {
            verifyBtn.isEnabled = true
        }.launchIn(lifecycleScope)


        timerView.setCircularTimerListener(object : CircularTimerListener {
            override fun updateDataOnTick(remainingTimeInMs: Long): String {
                return ceil((remainingTimeInMs / 1000f).toDouble()).toString()
            }

            override fun onTimerFinished() {
                viewModel.pressTimerView(true)
                verifyBtn.isEnabled = false
            }

        }, 60, TimeFormatEnum.SECONDS, 1)

        resendVerify.click {
            viewModel.apply {
                setInitialValues()
                pressTimerView(false)
                userResend()
            }
            verifyBtn.isEnabled = true
        }
        

        verifyBtn.click {
            viewModel.verifyUser(verifyInput.text.toString())
        }
        backBtn.click {
            viewModel.pressBack()
        }

      loadFlows()
    }

    private fun loadFlows() = binding.scope {
        lifecycleScope.launchWhenStarted {
            viewModel.disableVerifyLiveData.collect {
                verifyBtn.isEnabled = false
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.enableVerifyLiveData.collect {
                verifyBtn.isEnabled = true
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
            viewModel.timerViewLiveData.collect {
                when (it) {
                    true -> binding.resendVerify.visible()
                    false -> binding.resendVerify.gone()
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.initialValues.collect {
                timerView?.apply {
                    progress = 0f
                    startTimer()
                }
            }
        }

    }

}