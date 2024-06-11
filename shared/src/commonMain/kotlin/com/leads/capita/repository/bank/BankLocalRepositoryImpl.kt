package com.leads.capita.repository.bank

import com.leads.capita.CapitaDb
import com.leads.capita.bank.BankRepository
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.repository.RestUtil
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking

class BankLocalRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) :BankRepository{
    val db = CapitaDb(databaseDriverFactory.createDriver())
    private val bankListUrl: String = "/banks"

    override fun getBankList(): String {
        var response: String? = null

        runBlocking {
            try {
                response = RestUtil.getClient()
                    .get(urlString = "${RestUtil.BASE_URL}$bankListUrl").body()
            } catch (e: Exception) {
            }
        }

        return response.toString()
    }

    override fun getBranchList(bankId:Int): String {
        var response: String? = null

        runBlocking {
            try {
                response = RestUtil.getClient()
                    .get(urlString = "${RestUtil.BASE_URL}$bankListUrl/${bankId}/branches").body()
            } catch (e: Exception) {
            }
        }

        return response.toString()
    }
}