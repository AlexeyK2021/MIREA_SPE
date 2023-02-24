package ru.eco.automan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.eco.automan.databinding.FragmentMainPageBinding


class MainPage : Fragment() {
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Hello.text = "Hello from Fragment 😊"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}