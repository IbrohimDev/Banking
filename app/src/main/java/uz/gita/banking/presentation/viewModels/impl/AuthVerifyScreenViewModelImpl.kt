package uz.gita.banking.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.banking.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.banking.domain.usecase.interfaces.AuthUseCase
import uz.gita.banking.presentation.viewModels.interfaces.AuthVerifyScreenViewModel

import uz.gita.banking.utils.eventValueFlow
import uz.gita.banking.utils.isConnected

import javax.inject.Inject


@HiltViewModel
class AuthVerifyScreenViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,

):ViewModel(),AuthVerifyScreenViewModel{

    override val backLiveData = eventValueFlow<Unit>()
    override val enableVerifyLiveData = eventValueFlow<Unit>()
    override val disableVerifyLiveData= eventValueFlow<Unit>()
    override val progressLiveData= eventValueFlow<Boolean>()
    override val errorLivaData= eventValueFlow<String>()
    override val notInternetLiveData= eventValueFlow<Unit>()
    override val successLiveData= eventValueFlow<Unit>()
    override val pinScreenLiveData= eventValueFlow<Unit>()
    override val timerViewLiveData = eventValueFlow<Boolean>()
    override val initialValues = eventValueFlow<Unit>()

    init {
        viewModelScope.launch {
            disableVerifyLiveData.emit(Unit)
            initialValues.emit(Unit)
        }

    }
    override fun pressBack() {
        viewModelScope.launch {
            backLiveData.emit(Unit)
        }

    }

    override fun verifyUser(code: String) {
        if (!isConnected()) {
            notInternetLiveData.tryEmit( Unit)
            return
        }
        progressLiveData.tryEmit(true)
        disableVerifyLiveData.tryEmit(Unit)
        authUseCase.sendSmsVerify(code).onEach {
            progressLiveData.tryEmit(false)
            enableVerifyLiveData.tryEmit(Unit)
            it.onSuccess {
                successLiveData.tryEmit(Unit)
            }
            it.onFailure { throwable ->
                throwable.message?.let { it1 -> errorLivaData.tryEmit(it1) }
            }
        }.launchIn(viewModelScope)
    }

    override fun pressTimerView(pressState: Boolean) {
       timerViewLiveData.tryEmit(pressState)
    }

    override fun setInitialValues() {
       initialValues.tryEmit(Unit)
    }

    override fun userResend() {
        authUseCase.resendVerify()
    }

}