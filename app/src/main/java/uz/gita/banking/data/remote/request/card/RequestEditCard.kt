package uz.gita.banking.data.remote.request.card

data class RequestEditCard(
    val cardNumber :String,
    val newName :String
)