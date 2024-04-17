package com.leads.capita.service.account


import com.leads.capita.DatabaseDriverFactory
import com.leads.capita.api.account.AccountRepository
import com.leads.capita.api.account.AccountService
import com.leads.capita.repository.account.AccountLocalRepositoryImpl
import com.leads.capita.repository.account.AccountRepositoryImpl
import com.leads.capita.service.RuntimeProfile
import com.leads.capita.service.RuntimeProfile.LIVE_RUNTIME

object AccountFactory {

//    fun getService(): AccountService {
//        return AccountServiceImpl()
//    }
//    fun getRepository(): AccountRepository {
//
//        return if(RuntimeProfile.getCurrentRuntime() == LIVE_RUNTIME){
//            AccountRepositoryImpl()
//        }else{
//            AccountLocalRepositoryImpl()
//        }
//    }

    fun getRepository(databaseDriverFactory: DatabaseDriverFactory): AccountRepository {
        return if (RuntimeProfile.getCurrentRuntime() == LIVE_RUNTIME) {
            AccountRepositoryImpl()
        } else {
            AccountLocalRepositoryImpl(databaseDriverFactory)
        }
    }
}
