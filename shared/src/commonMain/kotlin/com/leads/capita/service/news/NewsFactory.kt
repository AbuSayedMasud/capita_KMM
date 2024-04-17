package com.leads.capita.service.news


import com.leads.capita.DatabaseDriverFactory
import com.leads.capita.api.news.NewsRepository
import com.leads.capita.api.news.NewsService

import com.leads.capita.repository.news.NewsLocalRepositoryImpl
import com.leads.capita.repository.news.NewsRepositoryImpl
import com.leads.capita.service.RuntimeProfile

object NewsFactory {
//    fun getService(): NewsService {
//        return NewsServiceImpl()
//    }

//    fun getRepository(): NewsRepository {
//        return if (RuntimeProfile.getCurrentRuntime() == RuntimeProfile.LIVE_RUNTIME) {
//            NewsRepositoryImpl()
//        } else {
//            NewsLocalRepositoryImpl()
//        }
//    }

    fun getRepository(databaseDriverFactory: DatabaseDriverFactory): NewsRepository {
        return if (RuntimeProfile.getCurrentRuntime() == RuntimeProfile.LIVE_RUNTIME) {
            NewsRepositoryImpl()
        } else {
            NewsLocalRepositoryImpl(databaseDriverFactory)
        }
    }
}
