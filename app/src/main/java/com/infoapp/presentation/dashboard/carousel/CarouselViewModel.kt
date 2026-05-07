package com.infoapp.presentation.dashboard.carousel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infoapp.domain.model.CarouselItem
import com.infoapp.domain.usecase.GetCarouselItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface CarouselUiState {
    data object Loading : CarouselUiState
    data class Success(val items: List<CarouselItem>) : CarouselUiState
    data class Error(val message: String) : CarouselUiState
    data object Empty : CarouselUiState
}

@HiltViewModel
class CarouselViewModel @Inject constructor(
    private val getCarouselItemsUseCase: GetCarouselItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CarouselUiState>(CarouselUiState.Loading)
    val uiState: StateFlow<CarouselUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        _uiState.value = CarouselUiState.Loading
        viewModelScope.launch {
            getCarouselItemsUseCase().collect { result ->
                _uiState.value = result.fold(
                    onSuccess = { items ->
                        if (items.isEmpty()) CarouselUiState.Empty
                        else CarouselUiState.Success(items)
                    },
                    onFailure = { e ->
                        CarouselUiState.Error(e.message ?: "Failed to load carousel")
                    }
                )
            }
        }
    }
}