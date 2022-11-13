package ru.eg.sellersapplication.presentation.ui.fragments.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.eg.sellersapplication.databinding.FragmentSellerBinding

class FragmentSeller: Fragment() {

    private var _binding: FragmentSellerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellerBinding.inflate(inflater, container, false)

        //
        val scanQrCodeLauncher = registerForActivityResult(ScanQRCode()) {}
        binding.sellerButtonScanQR.setOnClickListener {
            scanQrCodeLauncher.launch(null)
        }

        return binding.root
    }



    private fun setClickListeners() {
        TODO("Set click listeners")
    }
}