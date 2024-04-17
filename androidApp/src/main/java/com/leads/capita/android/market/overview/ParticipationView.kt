package com.leads.capita.android.market.overview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.market.overview.Participation


@Composable
fun ParticipationView(participation: List<Participation>) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Define sizes based on screen size
    val imageSize = (screenWidth * 0.09f).coerceAtMost(52.dp)
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f // Increase text space on large screens
    val valueColumnWeight =
        if (screenWidth > 600.dp) 2f else 1f // Increase value space on large screens
    val textSize = if (screenWidth > 600.dp) 14.sp else 12.sp

    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
//        Spacer(modifier = Modifier.height(4.dp))
        Card(
            modifier = Modifier.padding(top = paddingValue),
            elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            backgroundColor = backgroundColor,
            shape = MaterialTheme.shapes.large,
        ) {
            participation.forEach { participation ->
                Row(
                    modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(textColumnWeight)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Issues Advanced",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.5.sp,
                                color = contentColor,
                            )
                            Text(
                                text = participation.issuesAdvanced,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End,
                                color = contentColor,
                            )
                        }
                        Row(
                            modifier = Modifier.padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Issues Declined",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.5.sp,
                                color = contentColor,
                            )
                            Text(
                                text = participation.issuesDeclined,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 13.sp,
                                textAlign = TextAlign.End,
                                fontWeight = FontWeight.Bold,
                                color = contentColor,
                            )
                        }
                        Row(
                            modifier = Modifier.padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Issues Unchanged",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.5.sp,
                                color = contentColor,
                            )
                            Text(
                                text = participation.issuesUnchanged,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 13.sp,
                                textAlign = TextAlign.End,
                                fontWeight = FontWeight.Bold,
                                color = contentColor,
                            )
                        }
                    }
                }
            }
        }
    }
}
