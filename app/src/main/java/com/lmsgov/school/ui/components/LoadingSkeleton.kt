package com.lmsgov.school.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp

@Composable
fun LoadingSkeleton(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "skeleton")
    val shimmerAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmer"
    )

    val shimmerBrush = Brush.linearGradient(
        colors = listOf(
            Color.Gray.copy(alpha = shimmerAlpha * 0.4f),
            Color.Gray.copy(alpha = shimmerAlpha * 0.8f),
            Color.Gray.copy(alpha = shimmerAlpha * 0.4f)
        ),
        start = Offset.Zero,
        end = Offset(1000f, 1000f)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SkeletonBox(
            width = 140.dp,
            height = 16.dp,
            brush = shimmerBrush
        )
        SkeletonBox(
            width = 200.dp,
            height = 24.dp,
            brush = shimmerBrush
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SkeletonBox(
                width = 80.dp,
                height = 80.dp,
                brush = shimmerBrush
            )
            SkeletonBox(
                width = 80.dp,
                height = 80.dp,
                brush = shimmerBrush
            )
            SkeletonBox(
                width = 80.dp,
                height = 80.dp,
                brush = shimmerBrush
            )
        }
        SkeletonBox(
            width = 280.dp,
            height = 14.dp,
            brush = shimmerBrush
        )
        SkeletonBox(
            width = 240.dp,
            height = 14.dp,
            brush = shimmerBrush
        )
        Spacer(modifier = Modifier.height(8.dp))
        SkeletonBox(
            width = 280.dp,
            height = 120.dp,
            brush = shimmerBrush
        )
    }
}

@Composable
private fun SkeletonBox(
    width: Dp,
    height: Dp,
    brush: Brush
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(8.dp))
            .background(brush)
    )
}
