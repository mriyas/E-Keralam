package com.infoapp.presentation.theme

import androidx.compose.ui.graphics.Color

// ============================================
// Kerala-inspired palette
// Primary  : Deep backwater green (Palm/coconut groves)
// Secondary: Kasavu gold (traditional mundu border)
// Tertiary : Temple terracotta (Padmanabhaswamy / heritage architecture)
// Background: Off-white kasavu cream
// ============================================

// Primary — Backwater / Coconut grove green
val Primary = Color(0xFF0B6E4F)              // deep tropical green
val PrimaryContainer = Color(0xFFB8E6D2)     // soft mint-green
val OnPrimary = Color(0xFFFFFFFF)
val OnPrimaryContainer = Color(0xFF002117)

// Secondary — Kasavu gold (the iconic gold border)
val Secondary = Color(0xFFC9A227)            // antique kasavu gold
val SecondaryContainer = Color(0xFFFFEBB0)   // pale gold tint
val OnSecondary = Color(0xFFFFFFFF)
val OnSecondaryContainer = Color(0xFF3D2E00)

// Tertiary — Temple terracotta / heritage red
val Tertiary = Color(0xFFB23A2C)             // temple-laterite red
val TertiaryContainer = Color(0xFFFFD9D2)    // soft coral
val OnTertiary = Color(0xFFFFFFFF)
val OnTertiaryContainer = Color(0xFF3D0700)

// Surfaces — Kasavu off-white / cream
val Background = Color(0xFFFBF7EC)           // kasavu cream
val Surface = Color(0xFFFFFFFF)
val SurfaceVariant = Color(0xFFF1ECDA)       // muted cream
val OnBackground = Color(0xFF1B1C18)
val OnSurface = Color(0xFF1B1C18)
val OnSurfaceVariant = Color(0xFF4A4B43)

// Text
val TextPrimary = Color(0xFF1B1C18)
val TextSecondary = Color(0xFF4A4B43)

// Status
val Error = Color(0xFFB3261E)
val Success = Color(0xFF2E7D32)
val Warning = Color(0xFFE6A100)

// ============================================
// Card accent palette — soft Kerala-inspired tints
// (used for grid card backgrounds + borders)
// ============================================
val CardColors = listOf(
    Color(0xFFE8F5E9),   // pale palm green
    Color(0xFFFFF8E1),   // soft kasavu gold
    Color(0xFFFFEBEE),   // muted terracotta blush
    Color(0xFFE0F2F1),   // backwater teal mist
    Color(0xFFFFF3E0),   // warm sandalwood
    Color(0xFFF1F8E9),   // banana-leaf green tint
)

val CardBorderColors = listOf(
    Color(0xFF81C784),   // palm green
    Color(0xFFE6C45C),   // kasavu gold
    Color(0xFFE57373),   // terracotta
    Color(0xFF4DB6AC),   // backwater teal
    Color(0xFFFFB74D),   // sandalwood ochre
    Color(0xFFAED581),   // banana-leaf
)

// ============================================
// Optional gradients (for headers, banners, FABs)
// ============================================
val GradientBackwater = listOf(
    Color(0xFF0B6E4F),
    Color(0xFF15997A)
)

val GradientKasavu = listOf(
    Color(0xFFC9A227),
    Color(0xFFE6C45C)
)

val GradientSunsetVarkala = listOf(
    Color(0xFFB23A2C),
    Color(0xFFE6A100)
)