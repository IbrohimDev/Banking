package uz.gita.banking.data.remote.request.auth

data class RequestAuthVerify(
    val phone :String,
    val code :String,
)