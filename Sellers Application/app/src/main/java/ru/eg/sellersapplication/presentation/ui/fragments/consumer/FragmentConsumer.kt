package ru.eg.sellersapplication.presentation.ui.fragments.consumer

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.eg.sellersapplication.databinding.FragmentConsumerBinding
import ru.eg.sellersapplication.presentation.utils.constants.*
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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setClickListeners() {
        binding.consumerButtonMakeQR.setOnClickListener {
            val uuid = viewModelConsumer.getUuid()
            val sharedPrefs = activity?.getSharedPreferences(SHARED_DATA, Context.MODE_PRIVATE)!!
            val phone = sharedPrefs.getString(CUSTOMER_PHONE, "")!!
            val id = sharedPrefs.getString(CUSTOMER_ID_KEY, "")!!
            viewModelConsumer.createCode(MOCK_SELLER_ID, uuid, phone, id)

            binding.consumerButtonMakeQR.visibility = View.GONE
        }
    }

    private fun observeData() {
        viewModelConsumer.data.observe(viewLifecycleOwner) { data ->
            if (data != null)  {
                val cusId = activity
                    ?.getSharedPreferences(SHARED_DATA, Context.MODE_PRIVATE)!!
                    .getString(CUSTOMER_ID_KEY, "")!!

                val qrContent = data.value.plus("/").plus(cusId)
                viewModelConsumer.getCode(
                    qrContent,
                    binding.consumerImageQR.width,
                    binding.consumerImageQR.height
                )
            }
        }
    }

    private fun observeBitmap() {
        viewModelConsumer.bitmap.observe(viewLifecycleOwner) { bitmap ->
            if (bitmap != null) {
                binding.consumerImageQR.setImageBitmap(bitmap)
                binding.consumerImageQR.visibility = View.VISIBLE
            }
        }
    }

    private fun observeError() {
        viewModelConsumer.error.observe(viewLifecycleOwner) { error ->
                /*
                Тут обработка ошибок
                 */
        }
    }
}