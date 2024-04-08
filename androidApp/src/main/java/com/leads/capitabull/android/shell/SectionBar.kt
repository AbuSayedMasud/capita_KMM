package com.leads.capita.shell

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.* // ktlint-disable no-wildcard-imports
import com.leads.capita.ui.theme.White

@SuppressLint("RememberReturnType")
@Composable
fun SectionBar(
    sections: List<String>,
    selectedSection: Int,
    onSectionSelected: (Int) -> Unit,
    sectionBarColor: Color,
) {
    val isCustomTabRow = sections.size <= 3

    Surface(
        color = sectionBarColor, // Use the lighter color for the section bar
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (isCustomTabRow) {
            CustomTabRow(
                tabs = sections,
                selectedTabIndex = selectedSection,
                onTabClick = { index ->
                    onSectionSelected(index)
                },
                homeSectionBarColor = sectionBarColor,
            )
        } else {
            CustomScrollableTabRow(
                tabs = sections,
                selectedTabIndex = selectedSection,
                homeSectionBarColor = sectionBarColor,
                onTabClick = { index ->
                    onSectionSelected(index)
                },
            )
        }
    }
}

@Composable
fun CustomTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
    homeSectionBarColor: Color,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = if (isSystemInDarkTheme()) White else White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .customTabIndicatorOffset(
                        currentTabPosition = tabPositions[selectedTabIndex],
                        tabWidth = tabPositions[selectedTabIndex].width.value.dp,
                    ),
            )
        },
        backgroundColor = homeSectionBarColor,
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            val isDarkTheme = isSystemInDarkTheme()
            val contentColor = remember(isDarkTheme, selectedTabIndex, tabIndex) {
                if (isDarkTheme) {
                    if (selectedTabIndex == tabIndex) Color.White else Color.White.copy(alpha = 0.5f)
                } else {
                    if (selectedTabIndex == tabIndex) Color.White else Color.White.copy(alpha = 0.5f)
                }
            }
            Tab(
                selected = selectedTabIndex == tabIndex,
                onClick = { onTabClick(tabIndex) },
                text = {
                    Text(
                        text = tab,
                        fontFamily = FontFamily.Default,
                        color = contentColor,
                    )
                },
            )
        }
    }
}

@Composable
fun CustomScrollableTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
    homeSectionBarColor: Color,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = if (isSystemInDarkTheme()) White else White,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .customTabIndicatorOffset(
                        currentTabPosition = tabPositions[selectedTabIndex],
                        tabWidth = tabPositions[selectedTabIndex].width.value.dp,
                    ),
            )
        },
        backgroundColor = homeSectionBarColor,
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            val isDarkTheme = isSystemInDarkTheme()
            val contentColor = remember(isDarkTheme, selectedTabIndex, tabIndex) {
                if (isDarkTheme) {
                    if (selectedTabIndex == tabIndex) Color.White else Color.White.copy(alpha = 0.5f)
                } else {
                    if (selectedTabIndex == tabIndex) Color.White else Color.White.copy(alpha = 0.5f)
                }
            }
            Tab(
                selected = selectedTabIndex == tabIndex,
                onClick = { onTabClick(tabIndex) },
                text = {
                    Text(
                        text = tab,
                        fontFamily = FontFamily.Default,
                        color = contentColor,
                    )
                },
            )
        }
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp,
): Modifier = composed {
    composed(
        inspectorInfo = debugInspectorInfo {
            name = "customTabIndicatorOffset"
            value = currentTabPosition
        },
    ) {
        val currentTabWidth by animateDpAsState(
            targetValue = tabWidth,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
            label = "",
        )
        val indicatorOffset by animateDpAsState(
            targetValue = currentTabPosition.left,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
            label = "",
        )
        this.fillMaxWidth()
            .wrapContentSize(Alignment.BottomStart)
            .offset(x = indicatorOffset)
            .width(currentTabWidth)
    }
}
