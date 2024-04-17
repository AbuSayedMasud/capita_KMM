package com.leads.capita.service.news


import com.leads.capita.DatabaseDriverFactory
import com.leads.capita.api.news.News
import com.leads.capita.api.news.NewsService
import com.leads.capita.repository.news.NewsRepositoryImpl
import com.leads.capita.service.news.NewsFactory

class NewsServiceImpl(private var databaseDriverFactory: DatabaseDriverFactory) : NewsService {
    override fun getNewsService(): List<News> {
        val repository=NewsFactory.getRepository(databaseDriverFactory)
        return repository.getNews()
    }
}
