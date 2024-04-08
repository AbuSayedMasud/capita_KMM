package com.leads.capita.shell

import com.leads.capitabull.android.R


sealed class BottomBar(
    val route: String,
    val lightIcon: Int,
    val darkIcon: Int,
) {
    data object Home : BottomBar(
        route = "Home",
        lightIcon = R.drawable.home_48px,
        darkIcon = R.drawable.home_48px,
    )
    data object Market : BottomBar(
        route = "Market",
        lightIcon = R.drawable.monitoring_48px,
        darkIcon = R.drawable.monitoring_48px,
    )
    data object Portfolio : BottomBar(
        route = "Portfolio",
        lightIcon = R.drawable.portfolio_24px,
        darkIcon = R.drawable.portfolio_24px,
    ) {
        const val ARG_VALUE = "value"
    }

    data object Service : BottomBar(
        route = "Service",
        lightIcon = R.drawable.services_icon,
        darkIcon = R.drawable.services_icon,
    )
    data object Trade : BottomBar(
        route = "Trade",
        lightIcon = R.drawable.handshake_48px,
        darkIcon = R.drawable.handshake_48px,
    )
    data object Profile : BottomBar(
        route = "Profile",
        lightIcon = R.drawable.person_48px,
        darkIcon = R.drawable.person_48px,
    )
}
