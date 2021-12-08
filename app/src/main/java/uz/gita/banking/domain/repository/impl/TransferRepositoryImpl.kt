package uz.gita.banking.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.gita.banking.app.App
import uz.gita.banking.data.local.LocalStorage
import uz.gita.banking.data.remote.api.CardApi
import uz.gita.banking.data.remote.api.TransferApi
import uz.gita.banking.data.remote.request.profile.RequestTransferFee
import uz.gita.banking.data.remote.request.transfer.RequestMoneyTransfer
import uz.gita.banking.data.remote.response.auth.ResponseRegister
import uz.gita.banking.data.remote.response.transfer.ResponseMoneyTransfer
import uz.gita.banking.data.remote.response.transfer.ResponseTransferFee
import uz.gita.banking.domain.repository.interfaces.TransferRepository
import uz.gita.banking.utils.timber
import javax.inject.Inject


class TransferRepositoryImpl @Inject constructor(
    private val gson:Gson,
    private val prefs: LocalStorage,
    private val api: TransferApi
) : TransferRepository {

    override fun moneyTransfer(data: RequestMoneyTransfer): Flow<Result<ResponseMoneyTransfer>> = flow{

            val response = api.sendMoney(data)
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

    override fun transferFee(data: RequestTransferFee): Flow<Result<ResponseTransferFee>> = flow{

            val response = api.transferFee(data)
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