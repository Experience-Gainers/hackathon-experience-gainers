package ru.eg.sellersapplication.presentation.ui.fragments.consumer

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.eg.sellersapplication.R
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

        setClickListeners()

        observeData()
        observeBitmap()
        observeAnswer()
        observePrice()

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

        binding.consumerCardConfirmBill.setOnClickListener {
            val reqId = activity
                ?.getSharedPreferences(SHARED_DATA, Context.MODE_PRIVATE)!!
                .getString(CUSTOMER_ID_KEY, "")!!

            viewModelConsumer.sendAnswer(reqId)

            binding.consumerCardConfirmBill.visibility = View.GONE
        }
    }

    private fun observeData() {
        viewModelConsumer.data.observe(viewLifecycleOwner) { data ->
            if (data != null)  {
                val cusId = activity
                    ?.getSharedPreferences(SHARED_DATA, Context.MODE_PRIVATE)!!
                    .getString(CUSTOMER_ID_KEY, "")!!

                val qrContent = data.value.plus('/').plus(cusId)
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


                val reqId = activity
                    ?.getSharedPreferences(SHARED_DATA, Context.MODE_PRIVATE)!!
                    .getString(CUSTOMER_ID_KEY, "")!!

                viewModelConsumer.waitResponse(reqId)
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

    private fun observePrice() {
        viewModelConsumer.price.observe(viewLifecycleOwner) { price ->
            if (price != null) {
                binding.consumerTextPrice.text =
                    getString(R.string.consumer_text_textBillSum, price.value)
                binding.consumerImageQR.visibility = View.GONE
                binding.consumerCardConfirmBill.visibility = View.VISIBLE
            }
        }
    }

    private fun observeAnswer() {
        viewModelConsumer.answer.observe(viewLifecycleOwner) { answer ->
            when (answer.value) {
                "COMPLETED" -> {
                    binding.consumerIconStatus.setImageResource(R.drawable.icon_state_success)
                    binding.consumerTextStatus.text = getText(R.string.seller_text_stateSuccess)
                    binding.consumerCardStateStatus.visibility = View.VISIBLE
                }
                else -> {
                    binding.consumerIconStatus.setImageResource(R.drawable.icon_state_error)
                    binding.consumerTextStatus.text = getText(R.string.seller_text_stateError)
                    binding.consumerCardStateStatus.visibility = View.VISIBLE
                }
            }
        }
    }

}