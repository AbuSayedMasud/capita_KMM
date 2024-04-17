package com.leads.capita.service.overview


import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.market.TickerService
import com.leads.capita.market.overview.OverviewRepository
import com.leads.capita.repository.overview.OverviewLocalRepositoryImpl
import com.leads.capita.repository.overview.OverviewRepositoryImpl
import com.leads.capita.service.RuntimeProfile

object OverviewFactory {
//    fun getService(): TickerService {
//        return OverviewServiceImpl()
//    }

//    fun getRepository(): OverviewRepository {
//        if (RuntimeProfile.getCurrentRuntime() == RuntimeProfile.LIVE_RUNTIME) {
//            return OverviewRepositoryImpl()
//        } else {
//            return OverviewLocalRepositoryImpl(context)
//        }
//    }
    fun getRepository(databaseDriverFactory: DatabaseDriverFactory): OverviewRepository {
        if (RuntimeProfile.getCurrentRuntime() == RuntimeProfile.LIVE_RUNTIME) {
            return OverviewRepositoryImpl()
        } else {
            return OverviewLocalRepositoryImpl(databaseDriverFactory)
        }
    }
}
