package com.nicolas.ecommerce.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.ecommerce.domain.models.Product
import com.nicolas.ecommerce.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) :
    ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _list = MutableLiveData<List<Product>>()
    val list: LiveData<List<Product>> = _list

    init {
        viewModelScope.launch {
            _loading.value = true
            _list.value = getProductsUseCase.invoke()
            _loading.value = false
        }
    }

}