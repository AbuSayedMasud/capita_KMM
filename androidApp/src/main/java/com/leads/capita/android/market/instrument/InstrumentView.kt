package com.leads.capita.android.market.instrument

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leads.capita.formatnumber.formatNumberWithCommas

import com.leads.capita.android.theme.getCardColors
import com.leads.capita.api.market.Ticker


@SuppressLint("SuspiciousIndentation")
@Composable
fun InstrumentView(instrument: Ticker) {
    if (instrument.name.isBlank() || instrument.symbol.isBlank()) {
        return
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val imageSize = (screenWidth * 0.09f).coerceAtMost(52.dp)
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f // Increase text space on large screens
    val valueColumnWeight =
        if (screenWidth > 600.dp) 2f else 1f // Increase value space on large screens
    val textSize = if (screenWidth > 600.dp) 14.sp else 12.sp
    var changeColor = Color(0xFF15A80B)

    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    Card(
        modifier = Modifier.padding(top = paddingValue),
        elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
        shape = MaterialTheme.shapes.large,
        backgroundColor = backgroundColor,
    ) {
        Row(
            modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(textColumnWeight)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = instrument.symbol,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.5.sp,
                            color = contentColor,
                        ),

                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(),
                ) {
                    Text(
                        text = instrument.name.take(13) + if (instrument.name.length > 12) "..." else "",
                        style = MaterialTheme.typography.body2.copy(fontSize = 13.sp, color = contentColor),

                    )
                    Text("|", modifier = Modifier.padding(horizontal = 2.dp), fontSize = 13.sp)
                    Text(
                        text = instrument.market,
                        style = MaterialTheme.typography.body2.copy(fontSize = 13.sp, color = contentColor),

                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(valueColumnWeight)
                    .offset(x = -screenWidth * 0.01f),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.End),

                ) {
                    Text(
                        text = formatNumberWithCommas(instrument.close.toDouble(), 2),
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.5.sp,
                        ),
                        textAlign = TextAlign.End,
                        color = contentColor,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (instrument.change.toDouble() < 0) {
                    changeColor = Color(0xFFff0100)
                } else if (instrument.change.toDouble() == 0.00) {
                    changeColor = Color(0xFF005826)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.End),
                ) {
                    if (instrument.changeDirection == "true") {
                        Text(text = "+", color = changeColor)
                    }
                    Text(
                        text = instrument.change,
                        style = MaterialTheme.typography.body2.copy(fontSize = 13.sp),
                        color = changeColor,
                    )

                    Spacer(modifier = Modifier.padding(1.dp))

                    Text("(", fontSize = textSize, color = changeColor)
                    if (instrument.changeDirection == "true") {
                        Text(text = "+", color = changeColor)
                    }
                    Text(
                        text = instrument.changePercent,
                        style = MaterialTheme.typography.body2.copy(fontSize = 13.sp),
                        color = changeColor,
                    )
                    Text(")", fontSize = textSize, color = changeColor)
                }
            }
        }
    }
}
