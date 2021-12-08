package uz.gita.banking.presentation.viewModels.interfaces

import kotlinx.coroutines.flow.Flow

interface CardScreenViewModel {
    val addCardFlow:Flow<Unit>

    fun pressAddCard()
}