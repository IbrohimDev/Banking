package uz.gita.banking.presentation.viewModels.interfaces

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface SplashScreenViewModel {
    val openMainScreenLiveData :Flow<Unit>
    val openPinScreenLiveData :Flow<Unit>
    val openLoginScreenLiveData:Flow<Unit>
}