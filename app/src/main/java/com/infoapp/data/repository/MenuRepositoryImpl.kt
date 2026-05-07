package com.infoapp.data.repository

import com.infoapp.data.remote.FirebaseMenuDataSource
import com.infoapp.domain.model.MenuItem
import com.infoapp.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseMenuDataSource
) : MenuRepository {

    override fun getRootMenuItems(): Flow<Result<List<MenuItem>>> =
        dataSource.getRootMenuItems().map { result ->
            result.map { menuItems ->
                menuItems
            }
        }

    override fun getMenuItemsByParentId(parentId: String): Flow<Result<List<MenuItem>>> =
        dataSource.getMenuItemsByParentId(parentId).map { result ->
            result.map { dtos -> dtos }
        }

    override suspend fun getMenuItemById(id: String): Result<MenuItem> =
        dataSource.getMenuItemById(id).map { it }
}
