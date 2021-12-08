package uz.gita.banking.data.remote.request.auth

data class RequestAuthResend(
    val phone: String,
    val password: String,
)