package uz.gita.banking.domain.repository.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.auth.RequestAuthLogin
import uz.gita.banking.data.remote.request.auth.RequestAuthRegister
import uz.gita.banking.data.remote.request.auth.RequestAuthResend
import uz.gita.banking.data.remote.request.auth.RequestAuthVerify


interface AuthRepository {
    fun registerUser(data : RequestAuthRegister) : Flow<Result<Unit>>
    fun userLogin(authRequest: RequestAuthLogin): Flow<Result<Unit>>
    fun sendSmsVerify(code: String): Flow<Result<Unit>>
    fun userLogout(): Flow<Result<Unit>>
    fun userResend():Flow<Result<Unit>>
}