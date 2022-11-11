package ru.eg.sellersapplication.presentation.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.eg.sellersapplication.R
import ru.eg.sellersapplication.databinding.FramentLoginBinding

class LoginFragment : Fragment(R.layout.frament_login) {
    private lateinit var binding: FramentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FramentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}