package uz.gita.banking.presentation.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import uz.gita.banking.domain.usecase.interfaces.AppUseCase
import uz.gita.banking.presentation.viewModels.interfaces.PinViewModel
import uz.gita.banking.utils.eventValueFlow
import javax.inject.Inject

@HiltViewModel
class PinViewModelImpImpl @Inject constructor(
    private val appUseCase: AppUseCase
):ViewModel(),PinViewModel{
    override val mainScreen = eventValueFlow<Unit>()
    override val errorFlow = eventValueFlow<Unit>()

    override fun setMainScreen() {
        mainScreen.tryEmit(Unit)
    }

    override fun checkPin(pinCode:String) {
        if(appUseCase.checkPin(pinCode)){
            mainScreen.tryEmit(Unit)
        }else{
            errorFlow.tryEmit(Unit)
        }
    }

}