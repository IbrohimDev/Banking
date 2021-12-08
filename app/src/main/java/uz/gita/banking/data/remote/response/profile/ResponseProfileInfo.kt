package uz.gita.banking.data.remote.response.profile

data class ResponseProfileInfo(
    val data: DataUser
)

data class DataUser(

    val firstName: String,
    val lastName: String,
    val password: String,
    val phone: String
)
