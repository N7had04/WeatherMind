package com.nhdtech.apps.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nhdtech.apps.ui.CitiesUiState
import com.nhdtech.apps.ui.theme.Error

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: CitiesUiState,
    onNavigateBack: () -> Unit,
    onSearchCityTextChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onGetForecast: (String) -> Unit
) {
    val searchCityText = state.searchCityText
    val cities = state.cities

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchCityText,
                onValueChange = {
                    onSearchCityTextChange(it)
                    onSearch(it)
                },
                modifier = Modifier.weight(0.8f),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                },
                trailingIcon = {
                    if (searchCityText.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.clickable {
                                onSearchCityTextChange("")
                                onSearch("")
                            }
                        )
                    }
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onTertiary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
                    disabledTextColor = MaterialTheme.colorScheme.onTertiary
                )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Cancel",
                color = Error,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .weight(0.2f)
                    .clickable{
                        onSearchCityTextChange("")
                        onSearch("")
                        onNavigateBack()
                    }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when {
                searchCityText.isEmpty() -> {
                    Spacer(modifier = Modifier.height(250.dp))
                    Text(
                        text = "Search for a city",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.isLoading -> {
                    Spacer(modifier = Modifier.height(250.dp))
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.error != null -> {
                    Spacer(modifier = Modifier.height(250.dp))
                    Text(
                        text = state.error,
                        color = Error,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.cities.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(cities) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable{ onGetForecast(it.name) }
                            ) {
                                Column {
                                    Text(
                                        text = it.name,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        style = MaterialTheme.typography.headlineSmall
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = "${it.region}, ${it.country}",
                                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}
