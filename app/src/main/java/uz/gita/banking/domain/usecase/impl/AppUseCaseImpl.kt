package uz.gita.banking.domain.usecase.impl


import uz.gita.banking.data.enum.PinStatus
import uz.gita.banking.data.enum.StartEnum
import uz.gita.banking.domain.repository.impl.AppRepositoryImpl
import uz.gita.banking.domain.repository.interfaces.AppRepository
import uz.gita.banking.domain.usecase.interfaces.AppUseCase
import javax.inject.Inject


class AppUseCaseImpl  @Inject constructor(
    private val repository: AppRepository
) : AppUseCase {

    override fun startScreen(): StartEnum = repository.startScreen()
    override fun hasPin(): PinStatus = repository.hasPin()
    override fun setPinStatus(pinStatus: PinStatus) = repository.setPinStatus(pinStatus)
    override fun setPinCode(pinCode: String) = repository.setPinCode(pinCode)
    override fun checkPin(pinCode: String) :Boolean = repository.checkPin(pinCode)
}