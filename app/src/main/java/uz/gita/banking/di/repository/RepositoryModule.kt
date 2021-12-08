package uz.gita.banking.di.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.banking.domain.repository.impl.*
import uz.gita.banking.domain.repository.interfaces.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {


    @Binds
    @Singleton
    fun getAppRepository(impl:AppRepositoryImpl):AppRepository

    @Binds
    @Singleton
    fun getAuthRepository(impl:AuthRepositoryImpl):AuthRepository

    @Binds
    @Singleton
    fun getCardRepository(impl:CardRepositoryImpl):CardRepository

    @Binds
    @Singleton
    fun getProfileRepository(impl:ProfileRepositoryImpl):ProfileRepository

    @Binds
    @Singleton
    fun getTransferRepository(impl:TransferRepositoryImpl):TransferRepository

}