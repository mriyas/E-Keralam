package com.infoapp.data.remote

import com.google.firebase.database.IgnoreExtraProperties
import com.infoapp.domain.model.CarouselItem

@IgnoreExtraProperties
data class CarouselItemDto(
    val id: String = "",
    val imageUrl: String = "",
    val targetUrl: String = "",
    val title: String = "",
    val order: Int = 0,
    val isActive: Boolean = true
) {
    fun toDomain() = CarouselItem(
        id = id,
        imageUrl = imageUrl,
        targetUrl = targetUrl,
        title = title,
        order = order,
        isActive = isActive
    )
}