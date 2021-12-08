package uz.gita.banking.data.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.banking.data.remote.request.card.RequestAddCard
import uz.gita.banking.data.remote.request.card.RequestCardVerify
import uz.gita.banking.data.remote.request.card.RequestDeleteCard
import uz.gita.banking.data.remote.request.card.RequestEditCard
import uz.gita.banking.data.remote.response.card.*


interface CardApi {

    @POST("/api/v1/card/add-card")
    suspend fun addCard(@Body data: RequestAddCard): Response<ResponseAddCard>

    @POST("/api/v1/card/verify")
    suspend fun verify(@Body data: RequestCardVerify): Response<ResponseCardVerifyCode>

    @GET("/api/v1/card/all")
    suspend  fun getAll(): Response<ResponseAllCard>

    @POST("/api/v1/card/edit-card")
    suspend fun editCard(@Body data: RequestEditCard): Response<ResponseEditCard>

    @POST("/api/v1/card/delete-card")
    suspend  fun deleteCard(@Body data: RequestDeleteCard): Response<ResponseDeleteCard>
}