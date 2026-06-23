package com.infoapp.domain.model

data class CarouselItem(
    val id: String = "",
    val imageUrl: String = "",
    val targetUrl: String = "",
    val title: String = "",
    val order: Int = 0,
    val isActive: Boolean = true,
)