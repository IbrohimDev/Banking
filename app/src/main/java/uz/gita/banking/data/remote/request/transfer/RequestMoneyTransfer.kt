package uz.gita.banking.data.remote.request.transfer

data class RequestMoneyTransfer(
    val amount: Int,
    val receiverPan: String,
    val sender: Int
)