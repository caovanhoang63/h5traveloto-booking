package com.example.h5traveloto_booking.payment



data class PaymentRequest(
    val vnp_OrderInfo: String,
    val orderType: String,
    val amount: Int,
    val bankCode: String?,
    val language: String,
    val billingDetails: BillingDetails,
    val invoiceDetails: InvoiceDetails
)

data class BillingDetails(
    val mobile: String,
    val email: String,
    val fullName: String,
    val address: String,
    val city: String,
    val country: String,
    val state: String?
)

data class InvoiceDetails(
    val mobile: String,
    val email: String,
    val customer: String,
    val address: String,
    val company: String,
    val taxCode: String,
    val type: String
)

data class PaymentResponse(
    val code: String,
    val message: String,
    val data: String
)

/*interface PaymentService {
    @POST("your-payment-endpoint")
    suspend fun initiatePayment(@Body request: PaymentRequest): PaymentResponse
}*/
class PaymentViewModel {

}