package ru.eg.sellersapplication.presentation.ui.fragments.consumer

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.eg.sellersapplication.R
import ru.eg.sellersapplication.databinding.FragmentConsumerBinding
import java.util.UUID

class FragmentConsumer: Fragment() {
    private val viewModelConsumer by viewModel<ViewModelConsumer>()

    private var _binding: FragmentConsumerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConsumerBinding.inflate(inflater, container, false)

        binding.consumerButtonMakeQR.setOnClickListener{
            val requestId = UUID.randomUUID().toString()
            val accountID = UUID.randomUUID().toString()
            binding.consumerImageQR.setImageBitmap(getQrCodeBitmap(requestId, accountID))
        }

        return binding.root
    }

    // Генератор QR-кода, решение ниже
    // https://stackoverflow.com/questions/64443791/android-qr-generator-api

    fun getQrCodeBitmap(requestId: String, accountID: String): Bitmap {
        val size = 512 //pixels
        val qrCodeContent = "$requestId, $accountID" //собственно эта строка и будет содержимым куара, в дальнейшем нужно будет разделить
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    private fun setClickListeners() {
        TODO("Set listeners on button")
    }

    private fun observeBitmap() {
        viewModelConsumer.bitmap.observe(viewLifecycleOwner) { bitmap ->
            TODO("Binding bitmap")
        }
    }

    private fun observeError() {
        viewModelConsumer.error.observe(viewLifecycleOwner) { error ->
            if (error.isNotEmpty())
                TODO("Insert error")
        }
    }
}