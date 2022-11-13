package ru.eg.sellersapplication.presentation.ui.fragments.seller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eg.sellersapplication.data.repository.SellerRepository

class ViewModelSeller(
    private val sellerRepository: SellerRepository
): ViewModel() {
    private var data = emptyList<String>()

    fun setData(list: List<String>) {
        data = list
    }

    fun getData(): List<String> = data

    fun confirm(token: String, customerId: String, amount: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            sellerRepository.confirm(token, customerId, amount)
        }
    }
}