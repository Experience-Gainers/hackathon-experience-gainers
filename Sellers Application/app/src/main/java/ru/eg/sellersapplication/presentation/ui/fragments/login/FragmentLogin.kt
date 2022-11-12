package ru.eg.sellersapplication.presentation.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.eg.sellersapplication.databinding.FragmentLoginBinding

class FragmentLogin: Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        setButtonsClickListener()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setButtonsClickListener() {
        binding.loginButtonSeller.setOnClickListener { view ->
            val linkWithArgs = FragmentLoginDirections.loginToSeller()
            view.findNavController().navigate(linkWithArgs)
        }

        binding.loginButtonConsumer.setOnClickListener { view ->
            val linkWithArgs = FragmentLoginDirections.loginToConsumer()
            view.findNavController().navigate(linkWithArgs)
        }
    }
}