package uz.gita.banking.data.remote.response.card

data class ResponseAllCard(
	val data: Data
)

data class Data(
	val data: List<DataItem>
)

data class DataItem(
	val id: Int,
	val owner: String,
	val cardName: String,
	val balance: Int,
	val pan: String,
	val exp: String,
	val status: Int,
	val color:Int,
	val ignoreBalance:Boolean
)

