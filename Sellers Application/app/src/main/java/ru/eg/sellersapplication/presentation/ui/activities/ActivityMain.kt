package ru.eg.sellersapplication.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.eg.sellersapplication.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}