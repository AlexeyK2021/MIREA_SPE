package ru.eco.automan.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.databinding.FragmentAddAutoBinding
import ru.eco.automan.databinding.FragmentAutoInformationBinding
import ru.eco.automan.models.Auto
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel

class InfoAutoFragment : Fragment(R.layout.fragment_auto_information) {
    private val autoViewModel: AutoViewModel by viewModels { AutoViewModelFactory(AutoApplication.autoRepository) }
    private var _binding: FragmentAutoInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAutoInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            brandEditView.text = getEditableFromText(autoViewModel.currentAutoBrandName)
            modelEditView.text = getEditableFromText(autoViewModel.currentAutoModelName)
            fuelEditView.text = getEditableFromText(autoViewModel.currentAutoFuelTypeName)

        }
    }

    private fun getEditableFromText(text: String): Editable =
        Editable.Factory.getInstance().newEditable(text)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}