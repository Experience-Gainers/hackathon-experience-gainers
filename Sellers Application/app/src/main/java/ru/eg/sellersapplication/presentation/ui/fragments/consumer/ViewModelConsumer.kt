package ru.eg.sellersapplication.presentation.ui.fragments.consumer

import android.content.SharedPreferences
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.eg.sellersapplication.data.pojo.consumer.ConsumerTok
import ru.eg.sellersapplication.data.repository.ConsumerRepository
import ru.eg.sellersapplication.presentation.utils.constants.ErrorCodes
import ru.eg.sellersapplication.presentation.utils.constants.MOCK_SMS
import ru.eg.sellersapplication.presentation.utils.qr.qrCreator
import java.util.*

class ViewModelConsumer(
    private val consumerRepository: ConsumerRepository
): ViewModel() {
    private val _data = MutableLiveData<ConsumerTok>().apply { value = null }
    val data: LiveData<ConsumerTok> = _data

    private val _error = MutableLiveData<ErrorCodes>().apply { value = null }
    val error: LiveData<ErrorCodes> = _error

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap> = _bitmap

    fun getCode(content: String, width: Int, height: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = qrCreator(content, width, height)

            withContext(Dispatchers.Main) {
                if (result.isCreated)
                    _bitmap.postValue(result.bitmap)
                else
                    _error.postValue(ErrorCodes.QR_CREATE)
            }
        }
    }

    fun getUuid(): String = UUID.randomUUID().toString()

    fun createCode(merchId: String, uuid: String, phone: String, id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = consumerRepository.postConsumerData(merchId, uuid, phone, id)

            if (response.isSuccessful) {
                val smsData = response.body()!!
                /*
                Тут стоит отправка пользователем SMS кода
                 */
                val codeResult = consumerRepository.postCode(merchId, id, uuid, MOCK_SMS)

                if (codeResult.isSuccessful) {
                    _data.postValue(ConsumerTok(
                        codeResult.body()!!.tok.value,
                        codeResult.body()!!.tok.expDate
                    ))
                }
            } else {
                withContext(Dispatchers.Main) {
                    _error.postValue(ErrorCodes.AUTH_FAILED)
                }
            }
        }
    }

    fun waitResponse() {
        
    }
}