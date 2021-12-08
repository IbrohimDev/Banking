package uz.gita.banking.presentation.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.banking.data.remote.request.card.RequestAddCard
import uz.gita.banking.domain.usecase.interfaces.CardUseCase
import uz.gita.banking.presentation.viewModels.interfaces.AddCardViewModel
import uz.gita.banking.utils.eventFlow
import uz.gita.banking.utils.eventValueFlow
import uz.gita.banking.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class AddCardViewModelImpl @Inject constructor(
    private val cardUseCase: CardUseCase
) : ViewModel(), AddCardViewModel {
    override val enableAddFlow = eventFlow()
    override val disableAddFlow = eventFlow()
    override val processFlow = eventValueFlow<Boolean>()
    override val errorLivaData = eventValueFlow<String>()
    override val notInternetFlow = eventFlow()
    override val successFlow = eventFlow()
    override val backBtnFlow = eventFlow()

    init {
        disableAddFlow.tryEmit(Unit)
    }
    override fun pressBack() {

            backBtnFlow.tryEmit(Unit)

    }

    override fun addCard(requestAddCard: RequestAddCard) {
        if (!isConnected()) {
            viewModelScope.launch {
                notInternetFlow.emit(Unit)
            }
            return
        }
        processFlow.tryEmit(true)
        disableAddFlow.tryEmit(Unit)
        cardUseCase.addCard(requestAddCard).onEach {
            processFlow.tryEmit(false)
            enableAddFlow.tryEmit(Unit)
            it.onSuccess {
              successFlow.tryEmit(Unit)
            }
            it.onFailure {
                 it.message?.let { it1 -> errorLivaData.tryEmit(it1) }
            }
        }.launchIn(viewModelScope)
    }

}