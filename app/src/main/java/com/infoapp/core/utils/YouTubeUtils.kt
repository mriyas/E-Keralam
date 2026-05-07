package com.infoapp.core.utils

object YouTubeUtils {
    fun extractVideoId(url: String): String? {
        if (url.isBlank()) return null
        // Handles: youtu.be/ID, youtube.com/watch?v=ID, youtube.com/embed/ID
        val patterns = listOf(
            Regex("youtu\\.be/([a-zA-Z0-9_-]{11})"),
            Regex("[?&]v=([a-zA-Z0-9_-]{11})"),
            Regex("youtube\\.com/embed/([a-zA-Z0-9_-]{11})")
        )
        for (pattern in patterns) {
            val match = pattern.find(url)
            if (match != null) return match.groupValues[1]
        }
        return null
    }
}
