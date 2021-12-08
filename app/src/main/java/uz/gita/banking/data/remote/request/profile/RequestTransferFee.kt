package uz.gita.banking.data.remote.request.profile

data class RequestTransferFee(
    val amount: Int,
    val receiverPan: String,
    val sender: Int
)
