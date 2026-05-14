package com.infoapp.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.infoapp.domain.model.MenuItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseDataSource @Inject constructor(
    private val database: FirebaseDatabase
) {
    companion object {
        private const val NODE = "menu_items"
    }

    private val rootRef get() = database.getReference(NODE)

    fun getRootMenuItems(): Flow<Result<List<MenuItem>>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children
                    .mapNotNull { MenuItemDto.fromSnapshot(it) }
                    .filter { (_, dto) -> dto.parentId == null }
                    .map { (id, dto) -> dto.toDomain(id) }
                    .sortedBy { it.order }
                trySend(Result.success(items))
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(Result.failure(error.toException()))
            }
        }
        rootRef.addValueEventListener(listener)
        awaitClose { rootRef.removeEventListener(listener) }
    }

    fun getMenuItemsByParentId(parentId: String): Flow<Result<List<MenuItem>>> = callbackFlow {
        val query = rootRef.orderByChild("parentId").equalTo(parentId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children
                    .mapNotNull { MenuItemDto.fromSnapshot(it) }
                    .map { (id, dto) -> dto.toDomain(id) }
                    .sortedBy { it.order }
                trySend(Result.success(items))
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(Result.failure(error.toException()))
            }
        }
        query.addValueEventListener(listener)
        awaitClose { query.removeEventListener(listener) }
    }

    suspend fun getMenuItemById(id: String): Result<MenuItem> = runCatching {
        val snapshot = rootRef.child(id).get().await()
        val (key, dto) = MenuItemDto.fromSnapshot(snapshot)
            ?: throw NoSuchElementException("Menu item '$id' not found")
        dto.toDomain(key)
    }
}
