package com.example.testbasalam.framework.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.testbasalam.business.interactors.productList.GetProductList

class ProductViewModel
@ViewModelInject constructor(
    private val getProductList: GetProductList,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val products= getProductList.getAllProducts().asLiveData(viewModelScope.coroutineContext)
}