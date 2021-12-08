package uz.gita.banking.presentation.viewModels.interfaces

import kotlinx.coroutines.flow.Flow


interface PinViewModel {
    val mainScreen: Flow<Unit>
    val errorFlow:Flow<Unit>
    fun setMainScreen()
    fun checkPin(pinCode:String)
}