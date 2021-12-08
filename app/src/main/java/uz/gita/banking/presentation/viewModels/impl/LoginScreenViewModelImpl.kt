package uz.gita.banking.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.banking.data.remote.request.auth.RequestAuthLogin
import uz.gita.banking.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.banking.domain.usecase.interfaces.AuthUseCase
import uz.gita.banking.presentation.viewModels.interfaces.LoginScreenViewModel
import uz.gita.banking.utils.eventValueFlow
import uz.gita.banking.utils.isConnected

import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel(), LoginScreenViewModel {

    override val enableLoginLiveData = eventValueFlow<Unit>()
    override val disableLoginLiveData = eventValueFlow<Unit>()
    override val progressLiveData = eventValueFlow<Boolean>()
    override val errorLivaData = eventValueFlow<String>()
    override val successLiveData = eventValueFlow<Unit>()
    override val registerScreenLiveData = eventValueFlow<Unit>()
    override val notInternetLiveData = eventValueFlow<Unit>()


    init {
        disableLoginLiveData.tryEmit(Unit)
    }

    override fun userLogin(requestAuthLogin: RequestAuthLogin) {
        if (!isConnected()) {
            notInternetLiveData.tryEmit(Unit)
            return
        }
        progressLiveData.tryEmit(true)
        disableLoginLiveData.tryEmit(Unit)
        authUseCase.userLogin(requestAuthLogin).onEach {
            progressLiveData.tryEmit(false)
            enableLoginLiveData.tryEmit(Unit)
            it.onSuccess {
                successLiveData.tryEmit(Unit)
            }
            it.onFailure {throwable ->
                throwable.message?.let { it1 -> errorLivaData.tryEmit(it1) }
            }
        }.launchIn(viewModelScope)
    }

    override fun openRegisterScreen() {
        registerScreenLiveData.tryEmit(Unit)
    }




}