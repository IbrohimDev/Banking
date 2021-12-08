package uz.gita.banking.utils

import android.content.Context
import com.google.gson.Gson
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import timber.log.Timber
import uz.gita.banking.BuildConfig
import uz.gita.banking.data.enum.StartEnum
import uz.gita.banking.data.local.LocalStorage
import uz.gita.banking.data.remote.response.auth.ResponseVerify


fun OkHttpClient.Builder.addLoggingInterceptor(context: Context): OkHttpClient.Builder {
    HttpLoggingInterceptor.Level.HEADERS
    val logging = object :HttpLoggingInterceptor.Logger{
        override fun log(message: String) {
          Timber.tag("HTTP").d(message)
        }
    }
    if (BuildConfig.LOGGING) {
        addInterceptor(ChuckInterceptor(context))
        addInterceptor(HttpLoggingInterceptor(logging))
    }
    return this
}

fun addHeaderInterceptor(pref: LocalStorage) = Interceptor { chain ->
    val tokenReq = chain.request().newBuilder()
        .addHeader("token",pref.accessToken)
        .build()
    return@Interceptor chain.proceed(tokenReq)
}

fun refreshInterceptor(pref: LocalStorage) = Interceptor { chain ->
    val request = chain.request()

    val response = chain.proceed(request)
    if (response.code == 401) {
        response.close()

        val data = JSONObject()
        data.put("phone",pref.phoneNumber)
        val body =
            data.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val requestRefresh = request.newBuilder()
            .addHeader("refresh_token", pref.refreshToken)
            .method("POST", body)
            .url("${BuildConfig.BASE_URL}/api/v1/auth/refresh")
            .build()
        val respondRefresh = chain.proceed(requestRefresh)
        if (respondRefresh.code == 401) {
            pref.startScreen = StartEnum.LOGIN
            return@Interceptor respondRefresh
        }
        if (respondRefresh.code == 200) {
            respondRefresh.body?.let {
                val data = Gson().fromJson(it.string(), ResponseVerify::class.java)
                pref.accessToken = data.data.accessToken
                pref.refreshToken = data.data.refreshToken
            }
            respondRefresh.close()
            val requestTwo = request.newBuilder()
                .removeHeader("token")
                .addHeader("token", pref.accessToken)
                .build()
            return@Interceptor chain.proceed(requestTwo)
        }
    }
    return@Interceptor response
}

