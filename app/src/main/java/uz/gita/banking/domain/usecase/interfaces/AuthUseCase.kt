package uz.gita.banking.domain.usecase.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.auth.RequestAuthLogin
import uz.gita.banking.data.remote.request.auth.RequestAuthRegister
import uz.gita.banking.data.remote.request.auth.RequestAuthVerify


interface AuthUseCase {
    fun registerUser(data : RequestAuthRegister) : Flow<Result<Unit>>
    fun userLogin(authRequest: RequestAuthLogin): Flow<Result<Unit>>
    fun sendSmsVerify(code:String): Flow<Result<Unit>>
    fun userLogout(): Flow<Result<Unit>>
    fun resendVerify():Flow<Result<Unit>>
}