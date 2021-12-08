package uz.gita.banking.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.banking.data.enum.StartEnum
import uz.gita.banking.data.local.LocalStorage
import uz.gita.banking.data.remote.api.AuthApi
import uz.gita.banking.data.remote.request.auth.RequestAuthLogin
import uz.gita.banking.data.remote.request.auth.RequestAuthRegister
import uz.gita.banking.data.remote.request.auth.RequestAuthResend
import uz.gita.banking.data.remote.request.auth.RequestAuthVerify
import uz.gita.banking.data.remote.response.auth.ResponseRegister
import uz.gita.banking.domain.repository.interfaces.AuthRepository
import uz.gita.banking.utils.timber
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val prefs: LocalStorage,
    private val api: AuthApi,
    private val gson: Gson
) : AuthRepository {

    override fun registerUser(data: RequestAuthRegister): Flow<Result<Unit>> = flow {

        val response = api.register(data)
        if (response.isSuccessful) {
            prefs.phoneNumber = data.phone
            emit(Result.success(Unit))
        } else {
            var st = "Serverga ulanishda xatolik bo'ldi"
            response.errorBody()?.let {
                st = gson.fromJson(it.string(), ResponseRegister::class.java).smsCode
            }
            emit(Result.failure(Throwable(st)))
        }
    }.catch {
        timber(it.message.toString())
        emit(Result.failure(Throwable("Serverda muammo bor")))
    }.flowOn(Dispatchers.IO)

    override fun userLogin(authRequest: RequestAuthLogin): Flow<Result<Unit>> = flow {

        val response = api.login(authRequest)

        if (response.isSuccessful) {
            prefs.phoneNumber = authRequest.phone
            prefs.userPassword = authRequest.password
            emit(Result.success(Unit))
        } else {
            var st = "Serverga ulanishda xatolik bo'ldi"
            response.errorBody()?.let {
                st = gson.fromJson(it.string(), ResponseRegister::class.java).smsCode
            }
            emit(Result.failure(Throwable(st)))
        }
    }.catch {
        timber(it.message.toString())
        emit(Result.failure(Throwable("Serverga ulanishda xatolik bo'ldi")))
    }.flowOn(Dispatchers.IO)

    override fun sendSmsVerify(code: String): Flow<Result<Unit>> = flow {

        val response = api.verify(RequestAuthVerify(prefs.phoneNumber, code))
        if (response.isSuccessful) {
            prefs.startScreen = StartEnum.PIN
            response.body()?.let {
                prefs.refreshToken = it.data.refreshToken
                prefs.accessToken = it.data.accessToken
            }
            emit(Result.success(Unit))

        } else {
            var st = "Serverga ulanishda xatolik bo'ldi"
            response.errorBody()?.let {

                st = gson.fromJson(it.string(), ResponseRegister::class.java).smsCode
            }
            emit(Result.failure(Throwable(st)))
        }


    }.catch {
        timber(it.message.toString())
        emit(Result.failure(Throwable("Serverga ulanishda xatolik boldi")))
    }.flowOn(Dispatchers.IO)

    override fun userLogout(): Flow<Result<Unit>> = flow {

        val response = api.logout()
        if (response.isSuccessful) {
            prefs.startScreen = StartEnum.LOGIN
            emit(Result.success(Unit))
        } else {
            var st = "Serverga ulanishda xatolik bo'ldi"
            response.errorBody()?.let {
                st = gson.fromJson(it.string(), ResponseRegister::class.java).smsCode
            }
            emit(Result.failure(Throwable(st)))
        }

    }.catch {
        timber(it.message.toString())
        emit(Result.failure(Throwable("Serverga ulanishda xatolik boldi")))
    }.flowOn(Dispatchers.IO)

    override fun userResend(): Flow<Result<Unit>> = flow {

        val response = api.resend(RequestAuthResend(prefs.phoneNumber, prefs.userPassword))
        if (response.isSuccessful) {
            prefs.startScreen = StartEnum.PIN
            emit(Result.success(Unit))
        } else {
            var st = "Serverga ulanishda xatolik bo'ldi"
            response.errorBody()?.let {
                st = gson.fromJson(it.string(), ResponseRegister::class.java).smsCode
            }
            emit(Result.failure(Throwable(st)))
        }
    }.catch {
        timber(it.message.toString())
        emit(Result.failure(Throwable("Serverga ulanishda xatolik boldi")))
    }.flowOn(Dispatchers.IO)

}
