package uz.gita.banking.data.remote.response.transfer

data class ResponseOutcomes(
    val data: DataOutcomePage
)

data class DataOutcomePage(
    val pageNumber: Int,
    val data: List<DataOutcome>,
    val pageSize: Int,
    val totalCount: Int
)
data class DataOutcome(
    val amount: Float,
    val receiver: Int,
    val fee: Float,
    val time: Long,
    val status: Int
)

