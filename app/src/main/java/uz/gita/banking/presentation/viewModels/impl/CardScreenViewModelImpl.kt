package uz.gita.banking.presentation.viewModels.impl

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.gita.banking.presentation.viewModels.interfaces.CardScreenViewModel
import uz.gita.banking.utils.eventFlow
import javax.inject.Inject

@HiltViewModel
class CardScreenViewModelImpl @Inject constructor(

) :ViewModel(),CardScreenViewModel{
    override val addCardFlow = eventFlow()

    override fun pressAddCard() {
        viewModelScope.launch {
            addCardFlow.emit(Unit)
        }
    }

}