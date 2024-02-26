package com.nicolas.ecommerce.ui.viewmodels

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
) :
    ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _list = MutableLiveData<List<Product>>()
    val list: LiveData<List<Product>> = _list

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories

    init {
        viewModelScope.launch {
            _loading.value = true
            _list.value = getProductsUseCase.invoke()
            _loading.value = false
        }

        viewModelScope.launch {
            _loading.value = true
            _categories.value = getCategoriesUseCase.invoke()
            _loading.value = false
        }
    }

}