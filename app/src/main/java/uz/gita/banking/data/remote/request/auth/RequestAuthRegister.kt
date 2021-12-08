package uz.gita.banking.data.remote.request.auth

data class RequestAuthRegister(
    val firstName: String,
    val lastName: String,
    val password: String,
    val phone: String,
    val status: Int,
)