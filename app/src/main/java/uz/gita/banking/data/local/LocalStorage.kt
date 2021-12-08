package uz.gita.banking.data.local

import android.content.Context
import com.securepreferences.SecurePreferences
import uz.gita.banking.data.enum.PinStatus

import uz.gita.banking.data.enum.StartEnum
import uz.gita.banking.utils.pinScreen
import uz.gita.banking.utils.startScreen



class LocalStorage  constructor ( context: Context) {

    private val ACCESS_TOKEN = "accessToken"
    private val REFRESH_TOKEN = "refreshToken"
    private val START_SCREEN = "asdfghjkl"


    private val pref = SecurePreferences(context, "1234567", "ibrohim")
    var accessToken: String
        get() = pref.getString(ACCESS_TOKEN, "")!!
        set(token) = pref.edit().putString(ACCESS_TOKEN, token).apply()

    var refreshToken: String
        get() = pref.getString(REFRESH_TOKEN, "")!!
        set(token) = pref.edit().putString(REFRESH_TOKEN, token).apply()

    var stateCards: Boolean
        set(value) {
            pref.edit().putBoolean("state", value).apply()
        }
        get() = pref.getBoolean("state", false)!!

    var phoneNumber: String
        get() = pref.getString("phone", "")!!
        set(number) = pref.edit().putString("phone", number).apply()

    var startScreen: StartEnum
        set(value) = pref.edit().putString("startScreen", value.name).apply()
        get() = pref.getString("startScreen", StartEnum.LOGIN.name)!!.startScreen()
    var hasPin: Boolean
        set(value) = pref.edit().putBoolean("hasPin", value).apply()
        get() = pref.getBoolean("hasPin", true)
    var userPassword:String
        set(value) = pref.edit().putString("userPassword", value).apply()
        get() = pref.getString("userPassword", "")!!
    var pinCode:String
        set(value) = pref.edit().putString("pinCode", value).apply()
        get() = pref.getString("pinCode", "")!!
    var pinStatus:PinStatus
        set(value) = pref.edit().putString("pinStatus", value.name).apply()
        get() = pref.getString("pinStatus", PinStatus.WithPin.name)!!.pinScreen()

}