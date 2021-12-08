package uz.gita.banking.domain.repository.interfaces

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.profile.RequestTransferFee
import uz.gita.banking.data.remote.request.transfer.RequestMoneyTransfer
import uz.gita.banking.data.remote.response.transfer.ResponseMoneyTransfer
import uz.gita.banking.data.remote.response.transfer.ResponseTransferFee


interface TransferRepository {
    fun moneyTransfer(data: RequestMoneyTransfer): Flow<Result<ResponseMoneyTransfer>>
    fun transferFee(data: RequestTransferFee): Flow<Result<ResponseTransferFee>>

}