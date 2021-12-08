package uz.gita.banking.data.remote.request.auth

data class RequestAuthLogin(
    val phone: String,
    val password: String,
)