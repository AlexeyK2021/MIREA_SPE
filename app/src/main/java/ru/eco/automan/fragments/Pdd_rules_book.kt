package ru.eco.automan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.eco.automan.databinding.FragmentPddRulesBookBinding


class Pdd_rules_book : Fragment() {
    private var _binding: FragmentPddRulesBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPddRulesBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Hello.text = "Hello from Rules Fragment 😊"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}