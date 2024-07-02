package me.prashant.testdrivenshop.presentation.ui.screens.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
) {
    var searchText by remember { mutableStateOf(TextFieldValue(query)) }

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .height(56.dp)
                .shadow(4.dp, RoundedCornerShape(12.dp), true)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    RoundedCornerShape(12.dp),
                ),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    onQueryChanged(it.text)
                },
                modifier =
                    Modifier
                        .weight(1f),
                textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                placeholder = {
                    Text(
                        text = "Try search...",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                        color = Color.Gray,
                    )
                },
                colors =
                    TextFieldDefaults.colors().copy(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedTextColor = Color.LightGray,
                        cursorColor = Color.Black,
                    ),
                maxLines = 1,
                singleLine = true,
            )
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = Color(0xFF554BA1),
                    contentDescription = "Search Icon",
                )
            }
        }
    }
}
