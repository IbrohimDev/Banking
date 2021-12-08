package uz.gita.banking.data.remote.response.transfer

data class ResponseMoneyTransfer(
    val data: DataMoney
)

data class DataMoney(
    val amount: Float,
    val receiver: Int,
    val sender: Int,
    val fee: Float,
    val id: Int,
    val time: Long,
    val status: Int
)