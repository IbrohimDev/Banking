package uz.gita.banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import uz.gita.banking.data.remote.request.profile.RequestProfileEdit
import uz.gita.banking.data.remote.response.profile.ResponseProfileEdit
import uz.gita.banking.data.remote.response.profile.ResponseProfileInfo
import uz.gita.banking.domain.repository.impl.ProfileRepositoryImpl
import uz.gita.banking.domain.repository.interfaces.ProfileRepository
import uz.gita.banking.domain.usecase.interfaces.ProfileUseCase
import javax.inject.Inject


class ProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
): ProfileUseCase {

    override fun editProfile(request: RequestProfileEdit): Flow<Result<ResponseProfileEdit>> = repository.editProfile(request)

    override fun uploadAvatar(part: MultipartBody.Part): Flow<Result<Unit>> = repository.uploadAvatar(part)

    override fun getAvatar(): Flow<Result<ResponseBody>> = repository.getAvatar()

    override fun getProfile(): Flow<Result<ResponseProfileInfo>> = repository.getProfile()

}