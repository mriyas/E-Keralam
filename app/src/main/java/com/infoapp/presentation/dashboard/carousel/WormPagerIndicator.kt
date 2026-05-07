package com.infoapp.presentation.dashboard.carousel

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infoapp.presentation.theme.Primary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WormPagerIndicator(
    pagerState: PagerState,
    count: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Primary,
    inactiveColor: Color = Color(0xFFD1D5DB),
    dotSize: Dp = 7.dp,
    activeWidth: Dp = 22.dp,
    spacing: Dp = 6.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        repeat(count) { index ->
            val isActive = pagerState.currentPage == index
            val width by animateDpAsState(
                targetValue = if (isActive) activeWidth else dotSize,
                animationSpec = spring(stiffness = 400f),
                label = "indicator_width_$index"
            )
            Box(
                modifier = Modifier
                    .height(dotSize)
                    .width(width)
                    .clip(RoundedCornerShape(50))
                    .background(if (isActive) activeColor else inactiveColor)
            )
        }
    }
}