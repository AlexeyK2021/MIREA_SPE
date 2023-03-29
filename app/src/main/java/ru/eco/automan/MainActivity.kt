package ru.eco.automan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.eco.automan.databinding.ActivityMainBinding
import ru.eco.automan.models.Brand
import ru.eco.automan.models.Category
import ru.eco.automan.repositories.CategoryRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView = binding.bottomNavView
        navView.setupWithNavController(findNavController(R.id.nav_host_fragment))

        Log.d("Brands", AutoApplication.database.categoryDao().getAllCategories().toString())
    }
}