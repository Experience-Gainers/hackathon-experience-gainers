package ru.eg.sellersapplication.presentation.ui.fragments.consumer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
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

        return binding.root
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