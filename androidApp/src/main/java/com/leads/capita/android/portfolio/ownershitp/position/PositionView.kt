package com.leads.capita.android.portfolio.ownershitp.position

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leads.capita.formatnumber.formatNumberWithCommas

import com.leads.capita.android.theme.Black
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.White
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.theme.rememberWindowSizeClass
import com.leads.capita.api.account.AccountInstrument

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PositionView(
    accountInstrument: AccountInstrument,
    expandedIndexPosition: Int,
    onCardClicked: (Int) -> Unit,

    ) {
    val change = accountInstrument.change

    val isExpanded = expandedIndexPosition == accountInstrument.instrumentIndex.toInt()

    var expanded by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val imageSize = (screenWidth * 0.09f).coerceAtMost(52.dp)
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f
    val valueColumnWeight =
        if (screenWidth > 600.dp) 2f else 1f
    val textSize = if (screenWidth > 600.dp) 14.sp else 12.sp
    var changeColor = Color(0xFF15A80B)
    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        Card(
            modifier = Modifier.padding(top =paddingValue, bottom = 0.dp),
            elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            shape = MaterialTheme.shapes.large,
            onClick = { onCardClicked(accountInstrument.instrumentIndex.toInt()) },
            backgroundColor = backgroundColor,
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 6.dp,
                    end = 6.dp,
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(textColumnWeight)) {
                        Text(
                            text = accountInstrument.shortName,
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                            fontSize = 15.5.sp,
                            color = contentColor,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = accountInstrument.longName,
                            style = MaterialTheme.typography.body2,
                            fontSize = 13.sp,
                            color = contentColor,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(valueColumnWeight)
                            .padding(top = screenWidth * 0.001f),
                        horizontalAlignment = Alignment.End,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Text(
                                text = formatNumberWithCommas(accountInstrument.value, 2),
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                                fontSize = 15.5.sp,
                                textAlign = TextAlign.End,
                                color = contentColor,
                            )
                        }
                        if (change < 0) {
                            changeColor = Color(0xFFff0100)
                        } else if (change == 0.00) {
                            changeColor = Color(0xFF005826)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.padding(top = 8.dp),
                        ) {
                            if (change > 0) {
                                Text(text = "+", color = changeColor)
                            }
                            Text(
                                text = accountInstrument.closedPrice.toString(),
                                style = MaterialTheme.typography.body2,
                                fontSize = 13.sp,
                                textAlign = TextAlign.End,
                                color = changeColor,
                            )
                            Text("(", color = changeColor)
                            if (change > 0) {
                                Text(text = "+", color = changeColor)
                            }
                            Text(
                                text = accountInstrument.change.toString() + "%",
                                style = MaterialTheme.typography.body2,
                                fontSize = 13.sp,
                                textAlign = TextAlign.End,
                                color = changeColor,
                            )
                            Text(")", color = changeColor)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }

                AnimatedContent(
                    targetState = isExpanded,
                    label = "",
                ) { targetExpanded ->
                    AnimatedVisibility(
                        visible = targetExpanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically(),
                    ) {
                        if (isExpanded) {
                            Card(
                                modifier = Modifier.padding(top = 8.dp),
                                elevation = 0.dp,
                                backgroundColor = Color.Transparent,
                                shape = MaterialTheme.shapes.medium,
                                border = null,
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                ) {
                                    PositionItem("Total Quantity", accountInstrument.totalQuantity)
                                    PositionItem(
                                        "Salable Quantity",
                                        accountInstrument.salableQuantity
                                    )
                                    PositionItem("Average Cost", accountInstrument.averageCost)
                                    PositionItem("Total Cost", accountInstrument.totalCost)
                                    PositionItem("Close Price", accountInstrument.closePrice)
                                    PositionItem(
                                        "Unrealized Gain/Loss",
                                        accountInstrument.unrealizedGain
                                    )
                                    PositionItem("%Gain(Loss)", accountInstrument.gainPercent)
                                    PositionItem("%Cost value", accountInstrument.costValue)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PositionItem(label: String, value: Double) {
    val textColor = if (isSystemInDarkTheme()) White else Black
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f),
            color = textColor,
        )
        Text(
            text = formatNumberWithCommas(value, 2),
            style = MaterialTheme.typography.body2,
            fontSize = 13.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f),
            color = textColor,
        )
    }
}
