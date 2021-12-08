package uz.gita.banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.card.RequestAddCard
import uz.gita.banking.data.remote.request.card.RequestCardVerify
import uz.gita.banking.data.remote.request.card.RequestDeleteCard
import uz.gita.banking.data.remote.request.card.RequestEditCard
import uz.gita.banking.data.remote.response.card.DataItem
import uz.gita.banking.domain.repository.impl.CardRepositoryImpl
import uz.gita.banking.domain.repository.interfaces.CardRepository
import uz.gita.banking.domain.usecase.interfaces.CardUseCase
import javax.inject.Inject


class CardUseCaseImpl @Inject constructor(
    private val repository: CardRepository
): CardUseCase {

    override fun addCard(card: RequestAddCard): Flow<Result<Unit>> = repository.addCard(card)

    override fun editCard(card: RequestEditCard): Flow<Result<Unit>> = repository.editCard(card)

    override fun deleteCard(card: RequestDeleteCard): Flow<Result<Unit>> = repository.deleteCard(card)

    override fun cardVerify(card: RequestCardVerify): Flow<Result<Unit>> = repository.cardVerify(card)

    override fun getAllCard(): Flow<Result<List<DataItem>>> = repository.getAllCard()
}