package com.infoapp.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infoapp.core.utils.YouTubeUtils
import com.infoapp.domain.model.MenuItem
import com.infoapp.presentation.components.ErrorScreen
import com.infoapp.presentation.components.LoadingScreen
import com.infoapp.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (uiState is DetailUiState.Success) {
                        Text(
                            text = (uiState as DetailUiState.Success).item.name,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            maxLines = 1
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.background(
                    Brush.horizontalGradient(listOf(Primary, Color(0xFF38BDF8)))
                )
            )
        },
        containerColor = Background
    ) { padding ->
        AnimatedContent(
            targetState = uiState,
            transitionSpec = {
                fadeIn(tween(300)) togetherWith fadeOut(tween(200))
            },
            label = "detail_state",
            modifier = Modifier.padding(padding)
        ) { state ->
            when (state) {
                is DetailUiState.Loading -> LoadingScreen()
                is DetailUiState.Error -> ErrorScreen(state.message) { viewModel.load() }
                is DetailUiState.Success -> DetailContent(item = state.item)
            }
        }
    }
}

@Composable
private fun DetailContent(item: MenuItem) {
    val context = LocalContext.current
    val videoId = remember(item.youtubeUrl) {
        YouTubeUtils.extractVideoId(item.youtubeUrl)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // ── Hero / Icon banner ──────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Primary.copy(alpha = 0.15f),
                            Background
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            if (item.iconUrl.isNotBlank()) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(item.iconUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .border(3.dp, Primary.copy(alpha = 0.3f), CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            // ── Title ────────────────────────────────────────────────────
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = OnBackground
            )

            // ── Body text ────────────────────────────────────────────────
            if (item.text.isNotBlank()) {
                Spacer(Modifier.height(16.dp))
                SectionCard {
                    Text(
                        text = item.text,
                        style = MaterialTheme.typography.bodyLarge,
                        color = OnSurfaceVariant,
                        lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                    )
                }
            }

            // ── YouTube Player ───────────────────────────────────────────
            if (videoId != null) {
                Spacer(Modifier.height(20.dp))
                SectionLabel(icon = "🎬", label = "Video")
                Spacer(Modifier.height(8.dp))
                YoutubePlayerComposable(
                    videoId = videoId,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // ── Image ────────────────────────────────────────────────────
            if (item.imageUrl.isNotBlank()) {
                Spacer(Modifier.height(20.dp))
                SectionLabel(icon = "🖼️", label = "Gallery")
                Spacer(Modifier.height(8.dp))
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(item.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Content image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            1.dp,
                            Primary.copy(alpha = 0.2f),
                            RoundedCornerShape(16.dp)
                        ),
                    contentScale = ContentScale.FillWidth
                )
            }

            // ── Primary action button ────────────────────────────────────
            if (item.primaryButtonText.isNotBlank() && item.primaryButtonAction.isNotBlank()) {
                Spacer(Modifier.height(28.dp))
                Button(
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.primaryButtonAction))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            // handle gracefully
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary
                    )
                ) {
                    Icon(
                        Icons.Default.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = item.primaryButtonText,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SectionCard(content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
private fun SectionLabel(icon: String, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(icon, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.width(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Primary
        )
    }
}
