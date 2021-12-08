package uz.gita.banking.data.remote.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import uz.gita.banking.data.remote.request.profile.RequestProfileEdit
import uz.gita.banking.data.remote.response.profile.ResponseProfileEdit
import uz.gita.banking.data.remote.response.profile.ResponseProfileInfo
import uz.gita.banking.data.remote.response.profile.UploadAvatarResponse


interface ProfileApi {
    @Multipart
    @POST("/api/v1/profile/avatar")
    suspend fun uploadAvatar(@Part avatar : MultipartBody.Part) : Response<UploadAvatarResponse>

    @GET("/api/v1/profile/avatar")
    suspend fun getAvatar(): Response<ResponseBody>

    @PUT("/api/v1/profile")
    suspend fun editProfile(@Body data: RequestProfileEdit): Response<ResponseProfileEdit>

    @GET("/api/v1/profile/info")
    suspend fun profileInfo(): Response<ResponseProfileInfo>
}