package com.leads.capita.android.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leads.capita.android.theme.getCardColors


@Composable
fun FilterView(searchText: String, onIndexSelected: (String) -> Unit) {
    // Obtain the current screen configuration
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Define sizes based on screen size
    val imageSize = (screenWidth * 0.09f).coerceAtMost(52.dp)
    val textColumnWeight =
        if (screenWidth > 600.dp) 4f else 1f // Increase text space on large screens
    val valueColumnWeight =
        if (screenWidth > 600.dp) 2f else 1f // Increase value space on large screens
    val textSize = if (screenWidth > 600.dp) 14.sp else 12.sp

    // Get card colors based on the current theme
    val (backgroundColor, contentColor) = getCardColors()

    val filterItems = listOf(
        FilterItem("DSE", "Dhaka Stock Exchange Ltd."),
        FilterItem("CSE", "Chittagong Stock Exchange Ltd."),
    )

    // search filtered items based on their name, description
    val filteredItems = filterItems.filter { filterItem ->
        filterItem.name.contains(searchText, ignoreCase = true) ||
            filterItem.description.contains(searchText, ignoreCase = true)
    }

    // Composable LazyColumn for displaying the filter items
    LazyColumn {
        items(filteredItems) { filterItem ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .clickable { onIndexSelected(filterItem.name) },
                elevation = if (isSystemInDarkTheme()) 8.dp else 2.dp,
                shape = MaterialTheme.shapes.medium,
                backgroundColor = backgroundColor,
            ) {
                Row(
                    modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(8.dp)) // Space between Image and Texts

                    // Second Column - Texts
                    Column(modifier = Modifier.weight(textColumnWeight)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = filterItem.name,
                                fontSize = 15.5.sp,
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold, color = contentColor),
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(),
                        ) {
                            // Text for filter item description
                            Text(
                                text = filterItem.description,
                                fontSize = 13.sp,
                                style = MaterialTheme.typography.body2.copy(color = contentColor),
                            )
                        }
                    }
                }
            }
        }
        // Spacer to add some extra space at the end of the list
        item {
            Spacer(modifier = Modifier.height(76.dp))
        }
    }
}

// Data class representing a filter item
data class FilterItem(
    val name: String,
    val description: String,
)
