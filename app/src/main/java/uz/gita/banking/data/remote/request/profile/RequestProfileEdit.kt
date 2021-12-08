package uz.gita.banking.data.remote.request.profile

import com.google.gson.annotations.SerializedName

data class RequestProfileEdit(

    @field:SerializedName("firstName")
    val firstName: String,

    @field:SerializedName("lastName")
    val lastName: String,

    @field:SerializedName("password")
    val password: String
)
