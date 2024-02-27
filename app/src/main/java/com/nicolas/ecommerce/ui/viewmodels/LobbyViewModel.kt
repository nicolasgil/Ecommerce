package com.nicolas.ecommerce.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.domain.usecases.GetCategoriesUseCase
import com.nicolas.ecommerce.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _list = MutableLiveData<List<Product>>()
    val list: LiveData<List<Product>> = _list

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories

    init {
        viewModelScope.launch {
            try {
                _loading.value = true
                _list.value = getProductsUseCase.invoke()
            } catch (e: Exception) {
                Log.e("LobbyViewModel", "Error initializing list", e)
            } finally {
                _loading.value = false
            }
        }

        viewModelScope.launch {
            try {
                _loading.value = true
                _categories.value = getCategoriesUseCase.invoke()
            } catch (e: Exception) {
                Log.e("LobbyViewModel", "Error fetching categories", e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun tryAgainGetProducts() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _list.value = getProductsUseCase.invoke()
            } catch (e: Exception) {
                Log.e("LobbyViewModel", "Error fetching products", e)
            } finally {
                _loading.value = false
            }
        }
    }


    fun getProductById(productId: Int): Product? {
        return list.value?.find { it.id == productId }
    }
}
