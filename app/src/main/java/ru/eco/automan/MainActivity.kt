package ru.eco.automan

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


/**
 * Активность, на которой находятся все фрагменты
 */
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