package com.leads.capita.api.news



interface NewsRepository {
    fun getNews(): List<com.leads.capita.api.news.News>
    fun createNews(news: List<com.leads.capita.api.news.News>)
}
