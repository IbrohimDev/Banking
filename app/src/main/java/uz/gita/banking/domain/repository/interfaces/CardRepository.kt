package uz.gita.banking.domain.repository.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.card.RequestAddCard
import uz.gita.banking.data.remote.request.card.RequestCardVerify
import uz.gita.banking.data.remote.request.card.RequestDeleteCard
import uz.gita.banking.data.remote.request.card.RequestEditCard
import uz.gita.banking.data.remote.response.card.DataItem


interface CardRepository {
    fun addCard(card: RequestAddCard): Flow<Result<Unit>>
    fun editCard(card: RequestEditCard): Flow<Result<Unit>>
    fun deleteCard(card: RequestDeleteCard): Flow<Result<Unit>>
    fun cardVerify(card: RequestCardVerify): Flow<Result<Unit>>
    fun getAllCard(): Flow<Result<List<DataItem>>>
}