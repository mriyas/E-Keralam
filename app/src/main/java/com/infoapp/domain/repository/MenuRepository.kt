package com.infoapp.domain.repository

import com.infoapp.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getRootMenuItems(): Flow<Result<List<MenuItem>>>
    fun getMenuItemsByParentId(parentId: String): Flow<Result<List<MenuItem>>>
    suspend fun getMenuItemById(id: String): Result<MenuItem>
}
