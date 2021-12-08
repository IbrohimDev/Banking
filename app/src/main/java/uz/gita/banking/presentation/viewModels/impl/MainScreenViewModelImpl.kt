package uz.gita.banking.presentation.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.gita.banking.data.enum.BottomPageEnum
import uz.gita.banking.presentation.viewModels.interfaces.MainScreenViewModel
import uz.gita.banking.utils.eventFlow
import uz.gita.banking.utils.eventValueFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(

) : ViewModel(), MainScreenViewModel {
    override val openSelectPageByBottom = eventValueFlow<Int>()
    override val cardScreenFlow = eventFlow()
    private var selectPos = 0
    override fun selectPosPage(page: BottomPageEnum) {
        if (selectPos != page.pos) {
            selectPos = page.pos
            viewModelScope.launch {
                openSelectPageByBottom.emit(page.pos)
            }

        }
    }

    override fun pressCard() {
        viewModelScope.launch {
            cardScreenFlow.emit(Unit)
        }
    }

}