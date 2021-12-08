package uz.gita.banking.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.banking.BuildConfig.BASE_URL
import uz.gita.banking.data.local.LocalStorage
import uz.gita.banking.data.remote.api.AuthApi
import uz.gita.banking.data.remote.api.CardApi
import uz.gita.banking.data.remote.api.ProfileApi
import uz.gita.banking.data.remote.api.TransferApi
import uz.gita.banking.utils.addHeaderInterceptor
import uz.gita.banking.utils.addLoggingInterceptor
import uz.gita.banking.utils.refreshInterceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun getAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @[Provides Singleton]
    fun getCardApi(retrofit: Retrofit): CardApi = retrofit.create(CardApi::class.java)

    @[Provides Singleton]
    fun getProfileApi(retrofit: Retrofit): ProfileApi = retrofit.create(ProfileApi::class.java)

    @[Provides Singleton]
    fun getTransferApi(retrofit: Retrofit): TransferApi = retrofit.create(TransferApi::class.java)

    @[Provides Singleton]
    fun getGson():Gson = Gson()

    @[Provides Singleton]
    fun getRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @[Provides Singleton]
    fun getOkHttpClient(pref: LocalStorage, @ApplicationContext context: Context):OkHttpClient =
    OkHttpClient.Builder()
    .addLoggingInterceptor(context)
    .addInterceptor(addHeaderInterceptor(pref))
    .addInterceptor(refreshInterceptor(pref))
    .build()



}