package uz.gita.banking.presentation.viewModels.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.card.RequestAddCard

interface AddCardViewModel {

    val enableAddFlow: Flow<Unit>
    val disableAddFlow: Flow<Unit>
    val processFlow: Flow<Boolean>
    val errorLivaData: Flow<String>
    val notInternetFlow: Flow<Unit>
    val successFlow: Flow<Unit>
    val backBtnFlow:Flow<Unit>

    fun pressBack()
    fun addCard(requestAddCard: RequestAddCard)

}