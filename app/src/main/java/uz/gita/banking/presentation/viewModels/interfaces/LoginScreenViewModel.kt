package uz.gita.banking.presentation.viewModels.interfaces

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.auth.RequestAuthLogin

interface LoginScreenViewModel {
    val enableLoginLiveData: Flow<Unit>
    val disableLoginLiveData: Flow<Unit>
    val progressLiveData: Flow<Boolean>
    val errorLivaData: Flow<String>
    val notInternetLiveData: Flow<Unit>
    val successLiveData: Flow<Unit>
    val registerScreenLiveData:Flow<Unit>

    fun userLogin(requestAuthLogin:RequestAuthLogin)
    fun openRegisterScreen()
}