package com.infoapp.domain.repository


import com.infoapp.domain.model.CarouselItem
import kotlinx.coroutines.flow.Flow

interface CarouselRepository {
    fun getCarouselItems(): Flow<Result<List<CarouselItem>>>
}