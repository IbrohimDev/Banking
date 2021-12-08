package uz.gita.banking.data.remote.response.card

data class ResponseCardVerifyCode(
    val pan: String,
    val exp: String,
    val owner: String,
    val cardName: String,
    val balance: Long,
    val status: Int,
)
