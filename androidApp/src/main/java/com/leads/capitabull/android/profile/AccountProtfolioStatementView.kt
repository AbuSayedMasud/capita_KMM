package com.leads.capitabull.android.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.leads.capitabull.android.R
import com.leads.capitabull.android.shell.BottomBar
import com.leads.capitabull.android.theme.CapitaTheme
import com.leads.capitabull.android.theme.getCardColors
import com.leads.capitabull.android.theme.rememberWindowSizeClass
import com.leads.capitabull.android.theme.themeactivity.ColorSelectionViewModel

@Composable
fun AccountProtfolioStatementView(
    navController: NavHostController,
    colorSelectionViewModel: ColorSelectionViewModel,
) {
    val (backgroundColor, contentColor) = getCardColors()
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    val window = rememberWindowSizeClass()
    CapitaTheme(window, darkTheme = isSystemInDarkTheme()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = paddingValue),
            shadowElevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
            shape = MaterialTheme.shapes.large,
            color = backgroundColor,
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shadowElevation = 4.dp,
                color = backgroundColor,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 28.dp,
                        end = 16.dp,
                        bottom = 28.dp
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .weight(3f),
                    ) {
                        Text(
                            text = "Portfolio Statement",
                            style = MaterialTheme.typography.body1
                                .copy(
                                    fontWeight = FontWeight.Bold,
                                    color = contentColor,
                                ),
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .weight(1f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {
                                val bundle =
                                    navController.navigate("${BottomBar.Portfolio.route}/Position")
                            },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                            contentDescription = null,
                            tint = contentColor,
                        )
                    }
                }
            }
        }
    }
}
