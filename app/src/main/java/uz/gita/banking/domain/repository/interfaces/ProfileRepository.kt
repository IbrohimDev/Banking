package uz.gita.banking.domain.repository.interfaces

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import uz.gita.banking.data.remote.request.profile.RequestProfileEdit
import uz.gita.banking.data.remote.response.profile.ResponseProfileEdit
import uz.gita.banking.data.remote.response.profile.ResponseProfileInfo


interface ProfileRepository {
    fun editProfile(request: RequestProfileEdit): Flow<Result<ResponseProfileEdit>>
    fun uploadAvatar(part: MultipartBody.Part): Flow<Result<Unit>>

    fun getAvatar(): Flow<Result<ResponseBody>>
    fun getProfile(): Flow<Result<ResponseProfileInfo>>
}