package ru.eg.sellersapplication.presentation.ui.fragments.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.eg.sellersapplication.databinding.FragmentSellerBinding

class FragmentSeller: Fragment() {
    private val viewModelSeller by viewModel<ViewModelSeller>()

    private var _binding: FragmentSellerBinding? = null
    private val binding get() = _binding!!

    private val scanner = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            viewModelSeller.setData(result.contents.split('/'))
            binding.sellerButtonScanQR.visibility = View.GONE
            binding.sellerCardProcessing.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellerBinding.inflate(inflater, container, false)

        setClickListeners()

        return binding.root
    }



    private fun setClickListeners() {
        binding.sellerButtonScanQR.setOnClickListener {
            scanner.launch(ScanOptions())
        }

        binding.sellerButtonConfirmBill.setOnClickListener {
            val price = binding.sellerEditTextSumOfBill.text.toString().toDouble()
            val data = viewModelSeller.getData()
            viewModelSeller.confirm(data.get(0), data.get(1), price)

            binding.sellerCardProcessing.visibility = View.GONE
            binding.sellerCardStateStatus.visibility = View.VISIBLE
        }
    }
}