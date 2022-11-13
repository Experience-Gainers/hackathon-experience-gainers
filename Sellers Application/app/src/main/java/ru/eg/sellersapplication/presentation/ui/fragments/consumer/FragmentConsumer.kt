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
            binding.consumerImageQR.setImageBitmap(getQrCodeBitmap("30816ce4-396b-49d4-b75f-f0cbb13ff77e"))
        }

        return binding.root
    }

    // Генератор QR-кода, решение ниже
    // https://stackoverflow.com/questions/64443791/android-qr-generator-api
    fun getQrCodeBitmap(token: String): Bitmap {
        val size = 512 //pixels
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val bits = QRCodeWriter().encode(token, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    private fun setClickListeners() {
        TODO(reason = "Set listeners on button")
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