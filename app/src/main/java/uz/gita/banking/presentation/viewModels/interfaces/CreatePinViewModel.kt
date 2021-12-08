package uz.gita.banking.presentation.viewModels.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.enum.PinStatus

interface CreatePinViewModel {

    val pinScreen: Flow<Unit>
    val mainScreen:Flow<Unit>
    fun setMainScreen()
    fun setPinStatus(pinStatus: PinStatus)
    fun setPinCode(pinCode:String)
    fun pressPinScreen()

}