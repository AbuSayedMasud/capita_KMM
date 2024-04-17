package com.leads.capita.repository.news


import com.leads.capita.CapitaDb
import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.news.News
import com.leads.capita.news.NewsRepository

class NewsLocalRepositoryImpl(private var databaseDriverFactory: DatabaseDriverFactory) :
    NewsRepository {

    override fun getNews(): List<News> {
        val db = CapitaDb(databaseDriverFactory.createDriver())
        return db.newsQueries.getNewsData()
            .executeAsList()
            .map { news ->
                News(
                    title = news?.title!!,
                    description = news?.description!!,
                    date = news?.date!!,
                )
            }
    }

    override fun createNews(news: List<News>) {
        val db = CapitaDb(databaseDriverFactory.createDriver())
        news.forEach { newsdata ->
            val existingNews =
                db.newsQueries.getUniqueNews(newsdata.title)
            if (existingNews.executeAsList().isEmpty()) {
                db.newsQueries.insertNewsData(
                    title = newsdata.title,
                    description = newsdata.description,
                    date = newsdata.date,
                )
            }
        }
    }
}
