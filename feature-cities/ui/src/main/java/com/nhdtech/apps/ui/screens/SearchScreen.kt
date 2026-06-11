package com.nhdtech.apps.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nhdtech.apps.ui.CitiesUiState

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
            .background(Color.Black)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchCityText,
                onValueChange = {
                    onSearchCityTextChange(it)
                    onSearch(searchCityText)
                },
                modifier = Modifier.weight(0.8f),
                shape = RoundedCornerShape(16.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                },
                trailingIcon = {
                    if (searchCityText.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                onSearchCityTextChange("")
                                onSearch("")
                            }
                        )
                    }
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.DarkGray,
                    unfocusedContainerColor = Color.DarkGray,
                    disabledContainerColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Cancel",
                color = Color(0xFF0627C7),
                fontSize = 16.sp,
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

        if (searchCityText.isEmpty()) {
            Spacer(modifier = Modifier.height(250.dp))
            Text(
                text = "Search for a city",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        when {
            state.isLoading -> {
                Spacer(modifier = Modifier.height(250.dp))
                CircularProgressIndicator(
                    color = Color(0xFF0627C7),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            state.error != null -> {
                Spacer(modifier = Modifier.height(250.dp))
                Text(
                    text = state.error,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
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
                                    color = Color.White,
                                    fontSize = 24.sp
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "${it.region}, ${it.country}",
                                    color = Color.Gray,
                                    fontSize = 16.sp
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