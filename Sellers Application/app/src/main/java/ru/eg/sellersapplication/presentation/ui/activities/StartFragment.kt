package ru.eg.sellersapplication.presentation.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.eg.sellersapplication.R
import ru.eg.sellersapplication.databinding.FragmentStartBinding

class StartFragment: Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    //нужно поставить листнеры на кнопочки: при нажатии на соответствующую кнопку присваивается
    //соответствующий статус (покупатель -> STATUS = покупатель)
}