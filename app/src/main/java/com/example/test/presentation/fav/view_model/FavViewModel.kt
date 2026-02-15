package com.example.test.presentation.fav.view_model

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.test.data.model.Product
import com.example.test.data.repo.ProductsRepo
import kotlinx.coroutines.launch

class FavViewModel(private val productsRepo: ProductsRepo) : ViewModel() {

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
        getFavProducts()
    }

    fun getFavProducts(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                productsRepo.favProducts.asFlow().collect {
                    _products.value = it
                    _isLoading.value = false
                }
            }catch (e : Exception){
                _error.value = e.message.toString()
                _isLoading.value = false
            }
        }
    }

    fun deleteFavProduct(product: Product) {
        viewModelScope.launch {
            productsRepo.deleteFavProduct(product)
        }
    }


}

class FavFactory(private val context: Context) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val productsRepo = ProductsRepo(context)
        return FavViewModel(productsRepo) as T
    }
}