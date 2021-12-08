package uz.gita.banking.presentation.viewModels.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.enum.BottomPageEnum


interface MainScreenViewModel {
   val openSelectPageByBottom:Flow<Int>
   val cardScreenFlow:Flow<Unit>

   fun selectPosPage(page:BottomPageEnum)
   fun pressCard()
}