package ru.eg.sellersapplication.presentation.ui.fragments.consumer

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.eg.sellersapplication.data.repository.ConsumerRepository
import ru.eg.sellersapplication.presentation.utils.constants.QR_CREATE_ERROR
import ru.eg.sellersapplication.presentation.utils.qr.qrCreator

class ViewModelConsumer(
    private val consumerRepository: ConsumerRepository
): ViewModel() {
    //Variables for requests

    private val _error = MutableLiveData<String>().apply { value = null }
    val error: LiveData<String> = _error

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap> = _bitmap

    fun getCode(content: String, width: Int, height: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = qrCreator(content, width, height)

            withContext(Dispatchers.Main) {
                if (result.isCreated)
                    _bitmap.postValue(result.bitmap)
                else
                    _error.postValue(QR_CREATE_ERROR)
            }
        }
    }
}