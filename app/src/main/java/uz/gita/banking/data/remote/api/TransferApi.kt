package uz.gita.banking.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uz.gita.banking.data.remote.request.profile.RequestTransferFee
import uz.gita.banking.data.remote.request.transfer.RequestMoneyTransfer
import uz.gita.banking.data.remote.response.transfer.*


interface TransferApi {

    @POST("/api/v1/money-transfer/send-money")//
    suspend fun sendMoney(@Body data: RequestMoneyTransfer): Response<ResponseMoneyTransfer>

    @POST("/api/v1/money-transfer/fee")
    suspend fun transferFee(@Body data: RequestTransferFee): Response<ResponseTransferFee>

    @GET("/api/v1/money-transfer/history")
    suspend fun history(@Query("page_number") page_number:Int,
       @Query("page_size") page_size:Int): Response<ResponseMoneyTransferHistory>

    @GET("/api/v1/money-transfer/incomes")
    suspend fun incomes(@Query("page_number") page_number:Int,
                        @Query("page_size") page_size:Int): Response<ResponseIncomes>

    @GET("/api/v1/money-transfer/outcomes")
    suspend fun outcomes(@Query("page_number") page_number:Int,
                         @Query("page_size") page_size:Int): Response<ResponseOutcomes>
}