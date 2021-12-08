package uz.gita.banking.domain.repository.interfaces

import uz.gita.banking.data.enum.PinStatus
import uz.gita.banking.data.enum.StartEnum


interface AppRepository {
    fun startScreen(): StartEnum
    fun hasPin():PinStatus
    fun setPinStatus(pinStatus:PinStatus)
    fun setPinCode(pinCode:String)
    fun checkPin(pinCode:String):Boolean
}