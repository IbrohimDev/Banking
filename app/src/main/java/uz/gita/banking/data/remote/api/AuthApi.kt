package uz.gita.banking.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.banking.data.remote.request.auth.RequestAuthLogin
import uz.gita.banking.data.remote.request.auth.RequestAuthRegister
import uz.gita.banking.data.remote.request.auth.RequestAuthResend
import uz.gita.banking.data.remote.request.auth.RequestAuthVerify
import uz.gita.banking.data.remote.response.auth.*

interface AuthApi {
    @POST("/api/v1/auth/register")
    suspend fun register(@Body data: RequestAuthRegister): Response<ResponseRegister>

    @POST("/api/v1/auth/login")
    suspend fun login(@Body data: RequestAuthLogin): Response<ResponseLogin>

    @POST("/api/v1/auth/resend")
    suspend fun resend(@Body data: RequestAuthResend): Response<ResponseResend>

    @POST("/api/v1/auth/verify")
    suspend fun verify(@Body data: RequestAuthVerify): Response<ResponseVerify>

    @POST("/api/v1/auth/logout")
    suspend fun logout(): Response<ResponseLogOut>

}