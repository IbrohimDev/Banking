package uz.gita.banking.data.remote.request.card

data class RequestAddCard (
    val pan :String,
    val exp :String,
    val cardName :String
)