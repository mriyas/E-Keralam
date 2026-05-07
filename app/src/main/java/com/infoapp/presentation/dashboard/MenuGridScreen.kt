package com.infoapp.presentation.dashboard

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infoapp.domain.model.MenuItem
import com.infoapp.presentation.components.ErrorScreen
import com.infoapp.presentation.components.ShimmerGrid
import com.infoapp.presentation.theme.*
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuGridScreen(
    title: String = "Dashboard",
    isRoot: Boolean = true,
    onMenuItemClick: (MenuItem) -> Unit,
    onBackClick: (() -> Unit)? = null,
    viewModel: MenuGridViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val decodedTitle = remember(title) {
        try { URLDecoder.decode(title, "UTF-8") } catch (e: Exception) { title }
    }

    Scaffold(
        topBar = {
            DashboardTopBar(
                title = decodedTitle,
                isRoot = isRoot,
                onBackClick = onBackClick
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AnimatedContent(
                targetState = uiState,
                transitionSpec = {
                    fadeIn(animationSpec = tween(300)) togetherWith
                            fadeOut(animationSpec = tween(200))
                },
                label = "grid_state"
            ) { state ->
                when (state) {
                    is MenuGridUiState.Loading -> ShimmerGrid()
                    is MenuGridUiState.Error -> ErrorScreen(
                        message = state.message,
                        onRetry = { viewModel.load() }
                    )
                    is MenuGridUiState.Success -> {
                        if (state.items.isEmpty()) {
                            EmptyState()
                        } else {
                            MenuGrid(
                                items = state.items,
                                onItemClick = onMenuItemClick
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardTopBar(
    title: String,
    isRoot: Boolean,
    onBackClick: (() -> Unit)?
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        },
        navigationIcon = {
            if (!isRoot && onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.background(
            Brush.horizontalGradient(
                colors = listOf(Primary, Color(0xFF38BDF8))
            )
        )
    )
}


@Composable
private fun MenuGrid(
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    val animatedItems = remember { mutableSetOf<String>() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(
            items = items,
            key = { _, item -> item.id }
        ) { index, item ->

            val shouldAnimate = item.id !in animatedItems

            MenuCard(
                item = item,
                colorIndex = index % CardColors.size,
                animate = shouldAnimate,
                animationDelay = index * 60,
                onClick = { onItemClick(item) },
                onAnimated = {
                    animatedItems.add(item.id)
                }
            )
        }
    }
}

@Composable
private fun MenuCard(
    item: MenuItem,
    colorIndex: Int,
    animate: Boolean,
    animationDelay: Int,
    onClick: () -> Unit,
    onAnimated: () -> Unit
) {
    val targetScale = if (animate) 0.85f else 1f
    val targetAlpha = if (animate) 0f else 1f

    var started by remember(item.id) { mutableStateOf(false) }

    LaunchedEffect(animate) {
        if (animate && !started) {
            started = true
            kotlinx.coroutines.delay(animationDelay.toLong())
            onAnimated()
        }
    }

    val scale by animateFloatAsState(
        targetValue = if (animate && started) 1f else targetScale,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "card_scale"
    )

    val alpha by animateFloatAsState(
        targetValue = if (animate && started) 1f else targetAlpha,
        animationSpec = tween(300),
        label = "card_alpha"
    )

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardColors[colorIndex]),
        border = BorderStroke(1.5.dp, CardBorderColors[colorIndex])
    ) {
        MenuCardContent(item)
    }
}

@Composable
private fun MenuCardContent(item: MenuItem) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon
            Box(
                modifier = Modifier
                    //.size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                if (item.iconUrl.isNotBlank()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.iconUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = item.name.take(1).uppercase(),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Primary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            // Name
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                ),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = OnBackground
            )
        }

        // Sub-menu indicator badge
        if (item.hasSubMenu) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Has submenu",
                tint = Primary,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(18.dp)
                    .background(Color.White.copy(alpha = 0.8f), CircleShape)
            )
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("📭", fontSize = 56.sp)
        Spacer(Modifier.height(16.dp))
        Text(
            "No items here yet",
            style = MaterialTheme.typography.titleMedium,
            color = OnSurfaceVariant
        )
    }
}
