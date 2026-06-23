package com.infoapp.domain.model

data class MenuItem(
    val id: String = "",
    val name: String = "",
    val iconUrl: String = "",
    val order: Int = 0,
    val subMenuIds: List<String> = emptyList(),
    // Content fields (shown when no submenu)
    val text: String = "",
    val youtubeUrl: String = "",
    val imageUrl: String = "",
    val gallery: List<String> = emptyList(),
    val primaryButtonText: String = "",
    val primaryButtonAction: String = "",  // URL or deep link
    val parentId: String? = null
) {
    val hasSubMenu: Boolean get() = subMenuIds.isNotEmpty()
}
