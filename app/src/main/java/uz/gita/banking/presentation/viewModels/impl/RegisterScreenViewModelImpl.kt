package uz.gita.banking.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.banking.data.remote.request.auth.RequestAuthRegister
import uz.gita.banking.domain.usecase.impl.AuthUseCaseImpl
import uz.gita.banking.domain.usecase.interfaces.AuthUseCase
import uz.gita.banking.presentation.viewModels.interfaces.RegisterScreenViewModel

import uz.gita.banking.utils.eventValueFlow
import uz.gita.banking.utils.isConnected
import javax.inject.Inject


@HiltViewModel
class RegisterScreenViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase
) :ViewModel(),RegisterScreenViewModel{

    override val enableSignUpLiveData = eventValueFlow<Unit>()
    override val disableSignUpLiveData = eventValueFlow<Unit>()
    override val progressLiveData = eventValueFlow<Boolean>()
    override val errorLivaData = eventValueFlow<String>()
    override val notInternetLiveData = eventValueFlow<Unit>()
    override val successLiveData = eventValueFlow<Unit>()
    override val loginScreenLiveData = eventValueFlow<Unit>()

    init {
        disableSignUpLiveData.tryEmit(Unit)
    }
    override fun userRegister(requestAuthRegister: RequestAuthRegister) {
        if (!isConnected()) {
            notInternetLiveData.tryEmit(Unit)
            return
        }
        progressLiveData.tryEmit(true)
        disableSignUpLiveData.tryEmit(Unit)
        authUseCase.registerUser(requestAuthRegister).onEach {
            progressLiveData.tryEmit(false)
            enableSignUpLiveData.tryEmit(Unit)
            it.onSuccess {
                successLiveData.tryEmit(Unit)
            }
            it.onFailure {throwable ->
                throwable.message?.let { it1 -> errorLivaData.tryEmit(it1) }
            }
        }.launchIn(viewModelScope)
    }

    override fun pressLogin() {
        loginScreenLiveData.tryEmit(Unit)
    }

}