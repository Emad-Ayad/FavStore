package com.example.test.presentation.products.view_model

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.test.data.repo.ProductsRepo
import com.example.test.data.model.Product
import kotlinx.coroutines.launch


class ProductsViewModel(private val productsRepo: ProductsRepo) : ViewModel() {
    private val _products : MutableState<List<Product>> = mutableStateOf(emptyList())
    val products : State<List<Product>>
        get() = _products

    private val _error : MutableLiveData<String> = MutableLiveData()
    val error : LiveData<String>
        get() = _error

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    init {
        getAllProducts()
    }

    fun getAllProducts(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val products= productsRepo.getAllProducts()
                _products.value = products
                _isLoading.value = false
            }catch (e : Exception){
                _error.value = e.message.toString()
                _isLoading.value = false
            }
        }
    }

    fun insertFavProduct(product: Product) {
        viewModelScope.launch {
            productsRepo.insertFavProduct(product)
        }
    }

}

class ProductsFactory(private val context: Context) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val productsRepo = ProductsRepo(context)
        return ProductsViewModel(productsRepo) as T
    }
}