package com.infoapp.presentation.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infoapp.domain.model.MenuItem
import com.infoapp.domain.usecase.GetRootMenuItemsUseCase
import com.infoapp.domain.usecase.GetSubMenuItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// ─── State ────────────────────────────────────────────────────────────────────

sealed interface MenuGridUiState {
    data object Loading : MenuGridUiState
    data class Success(val items: List<MenuItem>) : MenuGridUiState
    data class Error(val message: String) : MenuGridUiState
}

// ─── ViewModel ────────────────────────────────────────────────────────────────

@HiltViewModel
class MenuGridViewModel @Inject constructor(
    private val getRootMenuItemsUseCase: GetRootMenuItemsUseCase,
    private val getSubMenuItemsUseCase: GetSubMenuItemsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // "ROOT" means top-level dashboard; otherwise it's a sub-menu parent id
    private val parentId: String? = savedStateHandle["menuId"]

    private val _uiState = MutableStateFlow<MenuGridUiState>(MenuGridUiState.Loading)
    val uiState: StateFlow<MenuGridUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.value = MenuGridUiState.Loading
            val flow = if (parentId == null || parentId == "ROOT") {
                getRootMenuItemsUseCase()
            } else {
                getSubMenuItemsUseCase(parentId)
            }
            flow.collect { result ->
                _uiState.value = result.fold(
                    onSuccess = { MenuGridUiState.Success(it) },
                    onFailure = { MenuGridUiState.Error(it.message ?: "Unknown error") }
                )
            }
        }
    }
}
