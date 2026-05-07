package com.infoapp.domain.usecase


import com.infoapp.domain.model.CarouselItem
import com.infoapp.domain.repository.CarouselRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarouselItemsUseCase @Inject constructor(
    private val repository: CarouselRepository
) {
    operator fun invoke(): Flow<Result<List<CarouselItem>>> = repository.getCarouselItems()
}