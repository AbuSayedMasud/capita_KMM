package com.leads.capitabull.android.market.overview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
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
import com.leads.capita.formatnumber.formatNumberWithCommas
import com.leads.capitabull.android.api.market.Ticker
import com.leads.capitabull.android.theme.Black
import com.leads.capitabull.android.theme.CapitaTheme
import com.leads.capitabull.android.theme.White
import com.leads.capitabull.android.theme.getCardColors
import com.leads.capitabull.android.theme.rememberWindowSizeClass


@Composable
fun TotalView(ticker: List<Ticker>) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val imageSize = (screenWidth * 0.09f).coerceAtMost(52.dp)
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f
    val valueColumnWeight =
        if (screenWidth > 600.dp) 2f else 1f
    val textSize = if (screenWidth > 600.dp) 14.sp else 12.sp
    val textColor = if (isSystemInDarkTheme()) White else Black

    val (backgroundColor, contentColor) = getCardColors()
    val window = rememberWindowSizeClass()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    CapitaTheme(window) {
//        Spacer(modifier = Modifier.height(4.dp))
        Card(
            modifier = Modifier.padding(top = paddingValue),
            elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            backgroundColor = backgroundColor,
            shape = MaterialTheme.shapes.large,
        ) {
            ticker.forEach { ticker ->
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
                                text = "Total Trade",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.5.sp,
                                color = contentColor,
                            )
                            Text(
                                text = formatNumberWithCommas(ticker.trade.toDouble(), 0),
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
                                text = "Total Volume",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.5.sp,
                                color = contentColor,
                            )
                            Text(
                                text = formatNumberWithCommas(ticker.volume.toDouble(), 0),
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
                                text = "Total Value in Taka (mn)",
                                fontSize = 15.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = contentColor,
                            )
                            Text(
                                text = formatNumberWithCommas(ticker.value.toDouble(), 2),
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
