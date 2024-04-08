package com.leads.capita.shell

import com.leads.capitabull.android.R


sealed class BottomBar(
    val route: String,
    val lightIcon: Int,
    val darkIcon: Int,
) {
    data object Home : BottomBar(
        route = "Home",
        lightIcon = R.drawable.homelight,
        darkIcon = R.drawable.homedark,
    )
    data object Market : BottomBar(
        route = "Market",
        lightIcon = R.drawable.market,
        darkIcon = R.drawable.marketdark,
    )
    data object Portfolio : BottomBar(
        route = "Portfolio",
        lightIcon = R.drawable.portfolio,
        darkIcon = R.drawable.portfolio,
    ) {
        const val ARG_VALUE = "value"
    }

    data object Service : BottomBar(
        route = "Service",
        lightIcon = R.drawable.service,
        darkIcon = R.drawable.service,
    )
    data object Search : BottomBar(
        route = "Search",
        lightIcon = R.drawable.search,
        darkIcon = R.drawable.search,
    )
    data object Account : BottomBar(
        route = "Account",
        lightIcon = R.drawable.account,
        darkIcon = R.drawable.account,
    )
}
