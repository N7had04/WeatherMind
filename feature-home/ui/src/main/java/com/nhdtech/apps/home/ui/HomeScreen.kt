package com.nhdtech.apps.home.ui

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nhdtech.apps.home.ui.components.ForecastCard
import com.nhdtech.apps.home.ui.components.PagerIndicator
import com.nhdtech.apps.ui.theme.Error

@SuppressLint("MissingPermission")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
    onNavigateToCities: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val locationLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) onPermissionGranted() else onPermissionDenied()
    }

    LaunchedEffect(state.hasAccessedLocationBefore) {
        if (!state.hasAccessedLocationBefore) {
            locationLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    val pagerState = rememberPagerState {
        state.savedForecasts.size
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .statusBarsPadding()
            .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .clickable { onNavigateToCities() }
                        .size(32.dp)
                )

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .clickable { onNavigateToSettings() }
                        .size(32.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.error != null -> {
                    Text(
                        text = state.error,
                        color = Error,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.savedForecasts.isNotEmpty() -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            pageSpacing = 16.dp
                        ) { page ->
                            if (page < state.savedForecasts.size) {
                                ForecastCard(
                                    forecast = state.savedForecasts[page],
                                    state = state
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        PagerIndicator(
                            pageCount = state.savedForecasts.size,
                            currentPage = pagerState.currentPage
                        )
                    }
                }
            }
        }
    }
}