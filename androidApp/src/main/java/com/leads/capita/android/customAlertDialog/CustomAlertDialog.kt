package com.leads.capita.android.customAlertDialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leads.capita.android.R
import com.leads.capita.android.theme.PrimaryColor
import com.leads.capita.android.theme.White
import com.leads.capita.android.theme.getCardColors


@Composable
fun CustomAlertDialog(
    message: String,
    isSuccess: Boolean,
    dismissState: MutableState<Boolean>
) {
    val (backgroundColor, contentColor) = getCardColors()
    AlertDialog(
        onDismissRequest = {
        },
        title = {
            // Display success or error icon based on the result
            Image(
                painter = if (isSuccess) {
                    painterResource(id = R.drawable.success)
                } else {
                    painterResource(id = R.drawable.high_importance_72)
                },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
            )
        },
        text = {
            // Display the success or error message
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    if (isSuccess) {
                        "successfully"
                    } else {
                        message
                    },
                    color = contentColor,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1
                        .copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = contentColor,
                            textAlign = TextAlign.Justify,
                        ),
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                // Button to close the dialog
                Button(
                    onClick = {
                        if (isSuccess) {
                            dismissState.value = false
                        } else {
                            dismissState.value = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryColor,
                        contentColor = White,
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 12.dp),
                ) {
                    Text("Ok")
                }
            }
        },
        backgroundColor = backgroundColor,
//            modifier = Modifier.size(270.dp, 200.dp),
    )
}
