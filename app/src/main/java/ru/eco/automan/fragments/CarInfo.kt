package ru.eco.automan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.eco.automan.databinding.FragmentCarInfoBinding


class CarInfo : Fragment() {
    private var _binding: FragmentCarInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Hello.text = "Hello from Car Info Fragment 😊"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}