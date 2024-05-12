package com.leads.capita.repository.payment

import com.leads.capita.payment.PaymentRepository
import com.leads.capita.payment.PaymentRequest
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.repository.RestUtil
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.runBlocking

class PaymentRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory):PaymentRepository{
    private val paymentPath: String = "/payments"
    private val paymentStatusPath: String = "/status/226475895"
    override fun getPayment(): String {
        val client = RestUtil.getClient()
        var responseContent: String? = null
        /* try {*/
        runBlocking {

            responseContent =
                client.post("${RestUtil.BASE_URL}$paymentPath") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        PaymentRequest(
                            accountCode = "1990",
                            transactionCode = "CHEQUE",
                            currency = "BDT",
                            amount = 50.00,
                            description = "STD-May-16.5"
                        )
                    )
                }.body()
        }

        return responseContent.toString()
    }

    override fun getPaymentStatus(): String {
        var response: String? = null

        runBlocking {
            try {
                response = RestUtil.getClient()
                    .get(urlString = "${RestUtil.BASE_URL}$paymentPath$paymentStatusPath").body()
            } catch (e: Exception) {
            }
        }

        return response.toString()
    }
}