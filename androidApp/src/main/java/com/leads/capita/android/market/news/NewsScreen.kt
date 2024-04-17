package com.leads.capita.android.market.news

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.leads.capita.repository.DatabaseDriverFactory


import com.leads.capita.android.theme.BackgroundColor
import com.leads.capita.news.News
import com.leads.capita.service.news.NewsServiceImpl


@Composable
fun NewsScreen() {
    val context = LocalContext.current
    var databaseDriverFactory = DatabaseDriverFactory(context)
    val newsService = NewsServiceImpl(databaseDriverFactory)

    var newsList: List<News>? by remember { mutableStateOf(null) }
    val newsData = newsService.getNewsService()
    newsList = newsData

    Box(
        modifier = Modifier.fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundColor else Color(0xfff9f9f9))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            itemsIndexed(newsList ?: emptyList()) { index, newsItem ->
                NewsView(
                    title = newsItem.title,
                    descriptor = newsItem.description,
                    date = newsItem.date,

                )
            }
        }
    }
}
