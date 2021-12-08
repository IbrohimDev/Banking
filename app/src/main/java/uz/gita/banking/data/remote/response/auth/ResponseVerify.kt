package uz.gita.banking.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class ResponseVerify(
    val data : TokenData
)

data class TokenData(
    @SerializedName("access_token")
    val accessToken : String,

    @SerializedName("refresh_token")
    val refreshToken : String,
)