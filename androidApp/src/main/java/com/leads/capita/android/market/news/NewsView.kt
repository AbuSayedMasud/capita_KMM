package com.leads.capita.android.market.news

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leads.capita.android.theme.CapitaTheme
import com.leads.capita.android.theme.getCardColors
import com.leads.capita.android.theme.rememberWindowSizeClass


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsView(
    title: String,
    descriptor: String,
    date: String,

) {
    var expanded by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f
    val valueColumnWeight =
        if (screenWidth > 600.dp) 2f else 1f
    val textSize = if (screenWidth > 600.dp) 14.sp else 12.sp
    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val window = rememberWindowSizeClass()
    CapitaTheme(window) {
        Card(
            modifier = Modifier.padding(top = paddingValue),
            elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            backgroundColor = backgroundColor,
            shape = MaterialTheme.shapes.large,
//            onClick = { expanded = !expanded },
        ) {
            Row(
                modifier = Modifier.padding(top = 32.dp, start = 8.dp, end = 8.dp, bottom = 32.dp),
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(textColumnWeight)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.5.sp,
                            color = contentColor,
                        ),

                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AnimatedVisibility(visible = expanded) {
                            Text(
                                text = descriptor,
                                style = MaterialTheme.typography.body2.copy(fontSize = 13.sp),
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                color = contentColor,
                            )
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AnimatedVisibility(visible = expanded) {
                            Text(
                                text = date,
                                style = MaterialTheme.typography.body2.copy(fontSize = 13.sp),
                                modifier = Modifier.padding(bottom = 0.dp),
                                color = contentColor,
                            )
                        }
                    }
                }
            }
        }
    }
}
