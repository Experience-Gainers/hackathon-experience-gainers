package ru.eg.sellersapplication.presentation.ui.fragments.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.journeyapps.barcodescanner.ScanContract
import ru.eg.sellersapplication.databinding.FragmentSellerBinding

class FragmentSeller: Fragment() {


    private var _binding: FragmentSellerBinding? = null
    private val binding get() = _binding!!

    private val codeScanContract = ScanContract()
    private val codeScanner = registerForActivityResult(codeScanContract) { result ->
        if (result.contents == null) {

        } else {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellerBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setClickListeners() {
        TODO("Set click listeners")
    }
}