package uz.gita.banking.data.remote.response.transfer

data class ResponseIncomes(
    val data: DataIncomePage
)

data class DataIncomePage(
    val pageNumber: Int,
    val data: List<DataIncome>,
    val pageSize: Int,
    val totalCount: Int
)

data class DataIncome(
    val amount: Double,
    val sender: Int,
    val fee: Double,
    val time: Long
)
