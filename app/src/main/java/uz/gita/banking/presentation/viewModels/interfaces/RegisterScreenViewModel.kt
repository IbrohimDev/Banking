package uz.gita.banking.presentation.viewModels.interfaces

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.auth.RequestAuthLogin
import uz.gita.banking.data.remote.request.auth.RequestAuthRegister

interface RegisterScreenViewModel {
    val enableSignUpLiveData:Flow<Unit>
    val disableSignUpLiveData:Flow<Unit>
    val progressLiveData:Flow<Boolean>
    val errorLivaData:Flow<String>
    val notInternetLiveData:Flow<Unit>
    val successLiveData:Flow<Unit>
    val loginScreenLiveData:Flow<Unit>


    fun userRegister(requestAuthRegister: RequestAuthRegister)
    fun pressLogin()
}