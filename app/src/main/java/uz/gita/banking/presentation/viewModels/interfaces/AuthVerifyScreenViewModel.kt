package uz.gita.banking.presentation.viewModels.interfaces

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface AuthVerifyScreenViewModel {
    val backLiveData:Flow<Unit>
    val enableVerifyLiveData:Flow<Unit>
    val disableVerifyLiveData:Flow<Unit>
    val progressLiveData:Flow<Boolean>
    val errorLivaData:Flow<String>
    val notInternetLiveData:Flow<Unit>
    val successLiveData:Flow<Unit>
    val pinScreenLiveData:Flow<Unit>
    val timerViewLiveData:Flow<Boolean>
    val initialValues:Flow<Unit>

    fun pressBack()
    fun verifyUser(code:String)
    fun pressTimerView(pressState:Boolean)
    fun setInitialValues()
    fun userResend()
}