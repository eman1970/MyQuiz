package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.myapplication.data.Statement
import com.example.myapplication.data.StatementRepository

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private val _quantity: MutableLiveData<Int> = MutableLiveData(0)
    val quantity: LiveData<Int> = _quantity

    private val _totalAmount: MutableLiveData<Int> = MutableLiveData()
    val totalAmount: LiveData<Int> = _totalAmount

    private val _info : MutableLiveData<Int> = MutableLiveData()

    var productRepository: StatementRepository = StatementRepository()

    val products: LiveData<List<Statement>> = liveData {
        val data = productRepository.getProducts()
        emit(data)
    }

    init {
        _quantity.value = 0    }


    fun increaseQuantity() {
        _quantity.value = _quantity.value!! + 1
    }

    fun decreaseQuantity() {
        if (_quantity.value!! > 0) {
            _quantity.value = _quantity.value!! - 1
        }

        fun checkout() {
            _totalAmount.value = _quantity.value!!

        }

    }
}