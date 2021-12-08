package uz.gita.banking.di.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.banking.domain.usecase.impl.*
import uz.gita.banking.domain.usecase.interfaces.*

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun getAppUseCase(impl:AppUseCaseImpl):AppUseCase

    @Binds
    fun getAuthUseCase(impl:AuthUseCaseImpl):AuthUseCase

    @Binds
    fun getCardUseCase(impl:CardUseCaseImpl):CardUseCase

    @Binds
    fun getProfileUseCase(impl:ProfileUseCaseImpl):ProfileUseCase

    @Binds
    fun getTransferUseCase(impl:TransferUseCaseImpl):TransferUseCase
}