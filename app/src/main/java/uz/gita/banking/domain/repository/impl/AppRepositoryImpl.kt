package uz.gita.banking.domain.repository.impl


import uz.gita.banking.app.App
import uz.gita.banking.data.enum.PinStatus
import uz.gita.banking.data.enum.StartEnum
import uz.gita.banking.data.local.LocalStorage
import uz.gita.banking.domain.repository.interfaces.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject  constructor(
    private val pref:LocalStorage
) : AppRepository {
    override fun startScreen(): StartEnum = pref.startScreen
    override fun hasPin(): PinStatus = pref.pinStatus
    override fun setPinStatus(pinStatus: PinStatus) {
        pref.pinStatus = pinStatus
    }
    override fun setPinCode(pinCode: String) {
        pref.pinCode = pinCode
    }

    override fun checkPin(pinCode: String): Boolean {
        return pref.pinCode == pinCode
    }
}