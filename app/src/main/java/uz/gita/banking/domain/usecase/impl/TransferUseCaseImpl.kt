package uz.gita.banking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.banking.data.remote.request.profile.RequestTransferFee
import uz.gita.banking.data.remote.request.transfer.RequestMoneyTransfer
import uz.gita.banking.data.remote.response.transfer.ResponseMoneyTransfer
import uz.gita.banking.data.remote.response.transfer.ResponseTransferFee
import uz.gita.banking.domain.repository.impl.TransferRepositoryImpl
import uz.gita.banking.domain.repository.interfaces.TransferRepository
import uz.gita.banking.domain.usecase.interfaces.TransferUseCase
import javax.inject.Inject


class TransferUseCaseImpl @Inject constructor(
    private val repository: TransferRepository
): TransferUseCase {

    override fun moneyTransfer(data: RequestMoneyTransfer): Flow<Result<ResponseMoneyTransfer>> = repository.moneyTransfer(data)
    override fun transferFee(data: RequestTransferFee): Flow<Result<ResponseTransferFee>> = repository.transferFee(data)
}