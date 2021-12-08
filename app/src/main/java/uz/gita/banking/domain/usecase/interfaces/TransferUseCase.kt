package uz.gita.banking.domain.usecase.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.profile.RequestTransferFee
import uz.gita.banking.data.remote.request.transfer.RequestMoneyTransfer
import uz.gita.banking.data.remote.response.transfer.ResponseMoneyTransfer
import uz.gita.banking.data.remote.response.transfer.ResponseTransferFee


interface TransferUseCase {
    fun moneyTransfer(data: RequestMoneyTransfer): Flow<Result<ResponseMoneyTransfer>>
    fun transferFee(data: RequestTransferFee): Flow<Result<ResponseTransferFee>>
}