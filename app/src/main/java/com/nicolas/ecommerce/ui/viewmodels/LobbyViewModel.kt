package com.nicolas.ecommerce.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.domain.usecases.GetCategoriesUseCase
import com.nicolas.ecommerce.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState(loading = true)
                _uiState.value = UiState(products = getProductsUseCase.invoke())
                _uiState.value = UiState(categories = getCategoriesUseCase.invoke())
            } catch (e: Exception) {
                Log.e("LobbyViewModel", "Error initializing list", e)
            } finally {
                _uiState.value = UiState(loading = false)
            }
        }
    }

    fun tryAgainGetProducts() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState(loading = true)
                _uiState.value = UiState(products = getProductsUseCase.invoke())
            } catch (e: Exception) {
                Log.e("LobbyViewModel", "Error fetching list", e)
            } finally {
                _uiState.value = UiState(loading = false)
            }
        }
    }


    fun getProductById(productId: Int): Product? {
        return _uiState.value.products?.find { it.id == productId }
    }

    data class UiState(
        val loading: Boolean = false,
        val products: List<Product>? = null,
        val categories: List<String>? = null,
    )
}
