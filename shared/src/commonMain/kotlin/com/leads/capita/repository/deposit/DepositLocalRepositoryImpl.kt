package com.leads.capita.repository.deposit

import com.leads.capita.desposit.DepositRepository
import com.leads.capita.desposit.DepositRequest
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.repository.RestUtil
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.runBlocking

class DepositLocalRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) : DepositRepository {
    private val depositPath = "/deposits"
    private val depositStatusPath = "/status/124"
    override fun getDeposit(): String {
        val client = RestUtil.getClient()
        var responseContent: String? = null
        /* try {*/
        runBlocking {

            responseContent =
                client.post("${RestUtil.BASE_URL}$depositPath") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        DepositRequest(
                            accountCode = "00DLB358",
                            routingNumber = "300150168",
                            transactionDate = "2023-03-19",
                            transactionCode = "CASH",
                            transactionRef = "feb-08-5",
                            amount = 140,
                            description = "Dev-Test",
                            status = "C"
                        )
                    )
                }.body()
        }

        return responseContent.toString()
    }

    override fun getDepositStatus(): String {
        var response: String? = null

        runBlocking {
            try {
                response = RestUtil.getClient()
                    .get(urlString = "${RestUtil.BASE_URL}$depositPath$depositStatusPath").body()
            } catch (e: Exception) {
            }
        }

        return response.toString()
    }
}