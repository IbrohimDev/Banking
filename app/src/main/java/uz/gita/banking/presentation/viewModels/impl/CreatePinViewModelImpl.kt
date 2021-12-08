package uz.gita.banking.presentation.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.gita.banking.data.enum.PinStatus
import uz.gita.banking.domain.usecase.interfaces.AppUseCase
import uz.gita.banking.presentation.viewModels.interfaces.CreatePinViewModel
import uz.gita.banking.utils.eventValueFlow
import javax.inject.Inject

@HiltViewModel
class CreatePinViewModelImpl @Inject constructor(
    private val appUseCase: AppUseCase
) :ViewModel(),CreatePinViewModel{
    override val pinScreen = eventValueFlow<Unit>()
    override val mainScreen = eventValueFlow<Unit>()

    override fun setPinStatus(pinStatus: PinStatus) = appUseCase.setPinStatus(pinStatus)
    override fun setPinCode(pinCode: String) = appUseCase.setPinCode(pinCode)
    override fun pressPinScreen() {
       viewModelScope.launch {
           pinScreen.emit(Unit)
       }
    }

    override fun setMainScreen() {
        mainScreen.tryEmit(Unit)
    }
}