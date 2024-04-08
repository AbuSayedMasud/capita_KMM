package com.leads.capita.ui.theme.themeactivity // ktlint-disable package-name

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

@Composable
fun ShowColorSelectionDialog(
    context: Context,
    viewModel: ColorSelectionViewModel,
    onDismiss: () -> Unit,
) {
    val colorList = listOf(
        Color(0xFF006A4E),
        Color(0xFFD32F2F),
        Color(0xFF1976D2),
        Color(0xFF9C27B0),
    )

    val selectedColor = remember { mutableStateOf(viewModel.appBarColor) }
    val homeSectionBarColor = rememberUpdatedState(viewModel.homeSectionBar)
    val portfolioSectionBarColor = rememberUpdatedState(viewModel.portfolioSectionBar)

    AlertDialog(
        onDismissRequest = { onDismiss },
        title = { Text(text = "Select Colors") },
        text = {
            Column {
                ColorPicker(
                    colors = colorList,
                    selectedColor = selectedColor.value,
                    onColorSelected = { color ->
                        selectedColor.value = color
                        viewModel.homeSectionBar = color
                        viewModel.portfolioSectionBar = color
                    },
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                viewModel.appBarColor = selectedColor.value
                viewModel.bottomNavBarColor = selectedColor.value
                viewModel.homeSectionBar = homeSectionBarColor.value // Use the updated homeSectionBarColor here
                viewModel.portfolioSectionBar = portfolioSectionBarColor.value
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        },
    )
}

