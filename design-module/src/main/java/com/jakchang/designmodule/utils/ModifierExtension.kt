package com.jakchang.deisgnlibrary.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

fun Modifier.horizontalFadingEdge(
    listState: LazyListState,
    length: Dp,
    edgeColor: Color? = null,
    firstIndexKey: Any,
    lastIndexKey: Any
) = composed {
    val color = edgeColor ?: MaterialTheme.colors.surface
    val isFirstItemFullyVisible = remember {
        derivedStateOf {
            listState.layoutInfo
                .visibleItemsInfo
                .any { it.key == firstIndexKey }.let { _isFirstIndexVisible ->
                    if (_isFirstIndexVisible) {
                        val layoutInfo = listState.layoutInfo
                        val firstItemInfo =
                            layoutInfo.visibleItemsInfo.firstOrNull() ?: return@let false

                        return@let firstItemInfo.offset == layoutInfo.viewportStartOffset
                    } else {
                        return@let false
                    }
                }
        }
    }

    val isLastItemFullyVisible = remember {
        derivedStateOf {
            listState.layoutInfo
                .visibleItemsInfo
                .any { it.key == lastIndexKey }.let { _isLastIndexVisible ->
                    if (_isLastIndexVisible) {
                        val layoutInfo = listState.layoutInfo
                        val lastItemInfo =
                            layoutInfo.visibleItemsInfo.lastOrNull() ?: return@let false

                        return@let lastItemInfo.size + lastItemInfo.offset == layoutInfo.viewportEndOffset
                    } else {
                        return@let false
                    }
                }
        }
    }

    drawWithContent {
        val lengthValue = length.toPx()

        drawContent()

        if (isFirstItemFullyVisible.value.not()) {
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        color,
                        Color.Transparent,
                    ),
                    startX = 0f,
                    endX = lengthValue,
                ),
                size = Size(
                    lengthValue,
                    this.size.height,
                ),
            )
        }

        if (isLastItemFullyVisible.value.not()) {
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        color,
                    ),
                    startX = size.width - lengthValue,
                    endX = size.width,
                ),
                topLeft = Offset(x = size.width - lengthValue, y = 0f),
            )
        }
    }
}
