package uz.gita.banking.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import timber.log.Timber
import uz.gita.banking.app.App
import uz.gita.banking.data.local.LocalStorage
import uz.gita.banking.data.remote.api.CardApi
import uz.gita.banking.data.remote.api.ProfileApi
import uz.gita.banking.data.remote.request.profile.RequestProfileEdit
import uz.gita.banking.data.remote.response.auth.ResponseRegister
import uz.gita.banking.data.remote.response.profile.ResponseProfileEdit
import uz.gita.banking.data.remote.response.profile.ResponseProfileInfo
import uz.gita.banking.domain.repository.interfaces.ProfileRepository
import uz.gita.banking.utils.timber
import javax.inject.Inject


class ProfileRepositoryImpl @Inject constructor(
    private val gson:Gson,
    private val prefs: LocalStorage,
    private val api: ProfileApi
) : ProfileRepository {

    override fun editProfile(request: RequestProfileEdit): Flow<Result<ResponseProfileEdit>> = flow {

            val response = api.editProfile(request)
            if (response.isSuccessful) {
                emit(Result.success(response.body()!!))
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

    override fun uploadAvatar(part: MultipartBody.Part): Flow<Result<Unit>> = flow {

            val response = api.uploadAvatar(part)
            if (response.isSuccessful) {
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

    override fun getAvatar(): Flow<Result<ResponseBody>> = flow {

            val response = api.getAvatar()
            if (response.isSuccessful) {
                emit(Result.success(response.body()!!))
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

    override fun getProfile(): Flow<Result<ResponseProfileInfo>> = flow{
            val response = api.profileInfo()
            if (response.isSuccessful) {
                emit(Result.success(response.body()!!))
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