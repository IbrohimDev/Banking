package uz.gita.banking.data.remote.request.card

data class RequestCardVerify(
    val pan: String,
    val code: String,
)