package com.leads.capita.android.service

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leads.capita.android.R
import com.leads.capita.android.theme.getCardColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ServiceView(navController: NavHostController) {
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

    val serviceItems = listOf(
        ServiceItem("Deposit", R.drawable.cheque, R.drawable.cheque2),
        ServiceItem("Payment", R.drawable.stop_cheque, R.drawable.withdraw2),
        ServiceItem("IPO", R.drawable.pay, R.drawable.ipo2),
        ServiceItem("Tax Certificate", R.drawable.certificate, R.drawable.certificate2),
        ServiceItem("Product Switch", R.drawable.pay_order, R.drawable.pay_order2),
    )
    val paddingValue = if (isSystemInDarkTheme()) {
        6.dp
    } else {
        10.dp
    }
    LazyColumn {
        items(serviceItems) { serviceItem ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = paddingValue, start = 0.dp, end = 0.dp, bottom = 0.dp),
                elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
                shape = MaterialTheme.shapes.large,
                backgroundColor = backgroundColor,
                onClick = {
                    when (serviceItem.title) {
                        "Deposit" -> {
                            navController.navigate("depositStatus")
                        }

                        "Payment" -> {
                            navController.navigate("payment")
                        }

                        "IPO" -> {
                            navController.navigate("ipo")
                        }

                        "Tax Certificate" -> {
                            navController.navigate("taxCertificate")
                        }
                        "Product Switch" -> {
                            navController.navigate("productSwitch")
                        }
                    }
                }

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Box(
                        modifier = Modifier.size(imageSize),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (isSystemInDarkTheme()) {
                            Image(
                                painter = painterResource(serviceItem.imageDarkResId),
                                contentDescription = null,
                            )
                        } else {
                            Image(
                                painter = painterResource(serviceItem.imageLightResId),
                                contentDescription = null,
                            )
                        }
                    }
                    Text(
                        text = serviceItem.title,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            color = contentColor
                        ),
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(76.dp))
        }
    }
}

data class ServiceItem(
    val title: String,
    val imageLightResId: Int,
    val imageDarkResId: Int,
)

