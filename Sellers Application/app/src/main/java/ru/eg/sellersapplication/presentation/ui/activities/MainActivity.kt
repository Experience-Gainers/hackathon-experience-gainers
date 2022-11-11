package ru.eg.sellersapplication.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.eg.sellersapplication.R

class MainActivity : AppCompatActivity() {
    /*
    Иницализация viewmodel такая:
    private val someViewModel by viewModel<SomeViewModel>()
    Возможна инциализация любого модуля через конструктор класса:
    class SomeClass(private val SomeModule): SomeInterface
     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}