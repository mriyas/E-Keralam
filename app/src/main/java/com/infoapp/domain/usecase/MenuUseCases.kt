package com.infoapp.domain.usecase

import com.infoapp.domain.model.MenuItem
import com.infoapp.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRootMenuItemsUseCase @Inject constructor(
    private val repository: MenuRepository
) {
    operator fun invoke(): Flow<Result<List<MenuItem>>> =
        repository.getRootMenuItems()
}

class GetSubMenuItemsUseCase @Inject constructor(
    private val repository: MenuRepository
) {
    operator fun invoke(parentId: String): Flow<Result<List<MenuItem>>> =
        repository.getMenuItemsByParentId(parentId)
}

class GetMenuItemByIdUseCase @Inject constructor(
    private val repository: MenuRepository
) {
    suspend operator fun invoke(id: String): Result<MenuItem> =
        repository.getMenuItemById(id)
}
