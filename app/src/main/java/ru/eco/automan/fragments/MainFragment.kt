package ru.eco.automan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.databinding.FragmentMainBinding

/**
 * Фрагмент, отвечающий за главную страницу приложения
 */
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        val view = binding.root

        binding.apply{
            settingsButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            }

            editAutoButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_infoAutoFragment)
            }

            moreExpencesImage.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_wastesListFragment)
            }

            finesButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_finesFragment)
            }

            pddButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_rulesChapterFragment)
            }

            autoImage.setOnClickListener {
                AutoApplication.notificationManager.createTestNotification(view.context)
            }
        }

        return view
    }
}