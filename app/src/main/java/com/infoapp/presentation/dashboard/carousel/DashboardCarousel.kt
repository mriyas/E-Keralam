package com.infoapp.presentation.dashboard.carousel

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infoapp.domain.model.CarouselItem
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardCarousel(
    modifier: Modifier = Modifier,
    viewModel: CarouselViewModel = hiltViewModel(),
    onItemClick: ((CarouselItem) -> Unit)? = null
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is CarouselUiState.Success -> {
            CarouselPager(
                items = state.items,
                modifier = modifier,
                onItemClick = onItemClick
            )
        }
        is CarouselUiState.Loading -> {
            CarouselShimmer(modifier = modifier)
        }
        // Hide on Empty/Error so the grid still shows nicely
        else -> Unit
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CarouselPager(
    items: List<CarouselItem>,
    modifier: Modifier = Modifier,
    onItemClick: ((CarouselItem) -> Unit)?
) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val uriHandler = LocalUriHandler.current

    // Auto-scroll
    LaunchedEffect(items.size) {
        if (items.size <= 1) return@LaunchedEffect
        while (true) {
            delay(3500)
            val next = (pagerState.currentPage + 1) % items.size
            pagerState.animateScrollToPage(
                page = next,
                animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
            )
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 24.dp),
            pageSpacing = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        ) { page ->
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).absoluteValue

            CarouselCard(
                item = items[page],
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val scale = lerp(0.92f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                        scaleX = scale
                        scaleY = scale
                        alpha = lerp(0.6f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                    },
                onClick = {
                    onItemClick?.invoke(items[page])
                        ?: items[page].targetUrl
                            .takeIf { it.isNotBlank() }
                            ?.let {
                                runCatching { uriHandler.openUri(it) }
                            }
                }
            )
        }

        Spacer(Modifier.height(10.dp))

        WormPagerIndicator(
            pagerState = pagerState,
            count = items.size,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 6.dp)
        )
    }
}

@Composable
private fun CarouselCard(
    item: CarouselItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF1F2937))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
        )

        // Bottom dark overlay for text legibility
        if (item.title.isNotBlank()) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.75f)
                            )
                        )
                    )
            )

            Text(
                text = item.title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            )
        }

        // Click overlay
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(20.dp))
                .clickable(onClick = onClick)
        )
    }
}