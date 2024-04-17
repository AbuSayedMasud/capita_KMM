package com.leads.capita.service.news


import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.news.News
import com.leads.capita.news.NewsService
import com.leads.capita.repository.news.NewsRepositoryImpl
import com.leads.capita.service.news.NewsFactory

class NewsServiceImpl(private var databaseDriverFactory: DatabaseDriverFactory) :
    NewsService {
    override fun getNewsService(): List<News> {
        val repository=NewsFactory.getRepository(databaseDriverFactory)
        return repository.getNews()
    }
}
