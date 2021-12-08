package uz.gita.banking.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.banking.data.local.LocalStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @[Provides Singleton]
    fun getSharedPrefs(@ApplicationContext context:Context):LocalStorage = LocalStorage(context)
}