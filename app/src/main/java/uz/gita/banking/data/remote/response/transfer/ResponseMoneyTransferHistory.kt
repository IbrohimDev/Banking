package uz.gita.banking.data.remote.response.transfer

sealed class ResponseMoneyTransferHistory{
    data class TransferResponse constructor(
        val data: TransferHistory
    )

    data class TransferHistory constructor(
        val pageNumber: Int,
        val pageSize: Int,
        val totalCount: Int,
        val data: List<HistoryData>
    )

    data class HistoryData constructor(
        val receiver: Int,
        val sender: Int,
        val amount: Float,
        val time: Long,
        val fee: Float,
        val status: Int = 0
    )
}
