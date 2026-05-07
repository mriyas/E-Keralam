// data/repository/CarouselRepositoryImpl.kt
package com.infoapp.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.infoapp.data.remote.CarouselItemDto
import com.infoapp.domain.model.CarouselItem
import com.infoapp.domain.repository.CarouselRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class CarouselRepositoryImpl @Inject constructor(
    @Named("dashboardCarouselsRef") private val carouselRef: DatabaseReference
) : CarouselRepository {

    override fun getCarouselItems(): Flow<Result<List<CarouselItem>>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val items = snapshot.children.mapNotNull { child ->
                        child.getValue(CarouselItemDto::class.java)?.copy(
                            id = child.key ?: ""
                        )
                    }
                        .filter { it.isActive && it.imageUrl.isNotBlank() }
                        .sortedBy { it.order }
                        .map { it.toDomain() }

                    trySend(Result.success(items))
                } catch (e: Exception) {
                    trySend(Result.failure(e))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Result.failure(error.toException()))
            }
        }

        carouselRef.addValueEventListener(listener)
        awaitClose { carouselRef.removeEventListener(listener) }
    }
}