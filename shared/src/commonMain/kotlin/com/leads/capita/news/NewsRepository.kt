package com.leads.capita.news



interface NewsRepository {
    fun getNews(): List<News>
    fun createNews(news: List<News>)
}
