package ru.eco.automan

import android.icu.text.Collator
import android.icu.text.Collator.getDisplayName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import ru.eco.automan.databinding.ActivityMainBinding
import java.util.Currency
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Log.d("MainActivity", Currency.getInstance(Locale.getDefault(Locale.Category.DISPLAY)).symbol)
//
//        Toast.makeText(
//            this,
//            Currency.getInstance(Locale.getDefault(Locale.Category.DISPLAY)).symbol,
//            Toast.LENGTH_LONG
//        ).show()

    }
}