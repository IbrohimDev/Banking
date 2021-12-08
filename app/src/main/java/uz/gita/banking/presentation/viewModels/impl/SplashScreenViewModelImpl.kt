package uz.gita.banking.presentation.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.banking.data.enum.PinStatus
import uz.gita.banking.data.enum.StartEnum
import uz.gita.banking.domain.usecase.interfaces.AppUseCase
import uz.gita.banking.presentation.viewModels.interfaces.SplashScreenViewModel
import uz.gita.banking.utils.eventValueFlow
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(
    private val appUseCase: AppUseCase
) : ViewModel(), SplashScreenViewModel {

    override val openMainScreenLiveData = eventValueFlow<Unit>()
    override val openPinScreenLiveData = eventValueFlow<Unit>()
    override val openLoginScreenLiveData = eventValueFlow<Unit>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            when (appUseCase.startScreen()) {
                StartEnum.LOGIN -> openLoginScreenLiveData.tryEmit(Unit)
                else -> {
                    if (appUseCase.hasPin() == PinStatus.WithPin) {
                        openPinScreenLiveData.tryEmit(Unit)
                    } else {
                        openMainScreenLiveData.tryEmit(Unit)
                    }

                }
            }
        }
    }

}