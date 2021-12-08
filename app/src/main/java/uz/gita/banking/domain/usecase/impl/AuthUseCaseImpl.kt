package uz.gita.banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.auth.RequestAuthLogin
import uz.gita.banking.data.remote.request.auth.RequestAuthRegister
import uz.gita.banking.data.remote.request.auth.RequestAuthVerify
import uz.gita.banking.domain.repository.impl.AuthRepositoryImpl
import uz.gita.banking.domain.repository.interfaces.AuthRepository
import uz.gita.banking.domain.usecase.interfaces.AuthUseCase
import javax.inject.Inject


class AuthUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : AuthUseCase {


    override fun registerUser(data: RequestAuthRegister): Flow<Result<Unit>> = repository.registerUser(data)

    override fun userLogin(authRequest: RequestAuthLogin): Flow<Result<Unit>> = repository.userLogin(authRequest)

    override fun sendSmsVerify(code: String): Flow<Result<Unit>> = repository.sendSmsVerify(code)

    override fun userLogout(): Flow<Result<Unit>> = repository.userLogout()
    override fun resendVerify(): Flow<Result<Unit>> = repository.userResend()
}