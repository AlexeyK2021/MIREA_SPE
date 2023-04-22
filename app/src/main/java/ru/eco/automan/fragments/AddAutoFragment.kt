package ru.eco.automan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.databinding.FragmentAddAutoBinding
import ru.eco.automan.models.Auto
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel

class AddAutoFragment : Fragment(R.layout.fragment_add_auto) {
    private val autoViewModel: AutoViewModel by viewModels { AutoViewModelFactory(AutoApplication.autoRepository) }
    private var _binding: FragmentAddAutoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            continueButton.setOnClickListener {
                autoViewModel.addNewAuto(
                    brand = brandEditView.text.toString(),
                    model = modelEditView.text.toString(),
                    manufactureYear = yearsEditView.text.toString().toInt(),
                    fuelType = fuelEditView.text.toString(),
                    registrationCertificateNumber = insuranceEditView.text.toString()
                )
            }

        }
    }

//    private fun saveDataToDb(newAuto: Auto) {
//
//        autoViewModel.addAuto(newAuto)
//
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}