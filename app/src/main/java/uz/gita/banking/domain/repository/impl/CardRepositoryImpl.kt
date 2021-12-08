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
import uz.gita.banking.data.remote.api.AuthApi
import uz.gita.banking.data.remote.api.CardApi
import uz.gita.banking.data.remote.request.card.RequestAddCard
import uz.gita.banking.data.remote.request.card.RequestCardVerify
import uz.gita.banking.data.remote.request.card.RequestDeleteCard
import uz.gita.banking.data.remote.request.card.RequestEditCard
import uz.gita.banking.data.remote.response.auth.ResponseRegister
import uz.gita.banking.data.remote.response.card.DataItem
import uz.gita.banking.domain.repository.interfaces.CardRepository
import uz.gita.banking.utils.timber
import javax.inject.Inject


class CardRepositoryImpl @Inject constructor(
    private val gson:Gson,
    private val prefs: LocalStorage,
    private val api: CardApi
): CardRepository {

    override fun addCard(card: RequestAddCard): Flow<Result<Unit>> = flow{

            val response = api.addCard(card)
            if (response.isSuccessful) {
                emit(Result.success(Unit))
            } else {
                var st = "Serverga ulanishda xatolik bo'ldi"
                response.errorBody()?.let {
                    st = gson.fromJson(it.string(), ResponseRegister::class.java).smsCode
                }
                emit(Result.failure(Throwable(st)))
            }
    }.catch{
        timber(it.message.toString())
        emit(Result.failure(Throwable("Serverga ulanishda xatolik boldi")))
    }.flowOn(Dispatchers.IO)

    override fun editCard(card: RequestEditCard): Flow<Result<Unit>> = flow {

            val response = api.editCard(card)
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

    override fun deleteCard(card: RequestDeleteCard): Flow<Result<Unit>> = flow {

            val response = api.deleteCard(card)
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

    override fun cardVerify(card: RequestCardVerify): Flow<Result<Unit>> = flow{

            val response = api.verify(card)
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

    override fun getAllCard(): Flow<Result<List<DataItem>>> = flow{

            val response = api.getAll()
            if (response.isSuccessful) {
                emit(Result.success(response.body()!!.data!!.data))
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