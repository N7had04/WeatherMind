package com.nhdtech.apps.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nhdtech.apps.domain.model.CitiesForecast
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

@Composable
fun ReorderableCityList(
    forecasts: List<CitiesForecast>,
    temperatureUnit: String,
    onDeleteForecast: (String) -> Unit,
    onReorder: (from: Int, to: Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val reorderState = rememberReorderableLazyListState(
        lazyListState = lazyListState,
        onMove = { from, to -> onReorder(from.index, to.index) }
    )

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        itemsIndexed(
            items = forecasts,
            key = { _, forecast -> forecast.locationName }
        ) { index, forecast ->
            ReorderableItem(
                state = reorderState,
                key = forecast.locationName
            ) { isDragging ->

                val elevation by animateDpAsState(
                    targetValue = if (isDragging) 8.dp else 0.dp,
                    label = "drag elevation"
                )
                val scale by animateFloatAsState(
                    targetValue = if (isDragging) 1.03f else 1f,
                    label = "drag scale"
                )

                SwipeToDeleteCard(
                    onDelete = { onDeleteForecast(forecast.locationName) },
                    isFirstItem = index == 0
                ) {
                    CityCard(
                        forecast = forecast,
                        isFirstItem = index == 0,
                        temperatureUnit = temperatureUnit,
                        isDragging = isDragging,
                        dragModifier = if (index != 0) {
                            Modifier.draggableHandle(
                                onDragStarted = { HapticFeedbackType.LongPress },
                            )
                        } else Modifier,
                        modifier = Modifier
                            .scale(scale)
                            .shadow(elevation, RoundedCornerShape(18.dp))
                    )
                }
            }
        }

        if (forecasts.size > 1) {
            item {
                Text(
                    text = "Swipe left to delete  ·  Hold & drag to reorder",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}