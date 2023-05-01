package ru.eco.automan.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.databinding.FragmentAddAutoBinding
import ru.eco.automan.databinding.FragmentAutoInformationBinding
import ru.eco.automan.models.Auto
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel

/**
 * Метод-расширение переводит текст из класса String в класс Editable для EditText
 */
fun String.getEditable(): Editable =
    Editable.Factory.getInstance().newEditable(this)

class InfoAutoFragment : Fragment(R.layout.fragment_auto_information) {
    private val autoViewModel: AutoViewModel by activityViewModels {
        AutoViewModelFactory(
            AutoApplication.autoRepository
        )
    }
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
        autoViewModel.currAuto.observe(viewLifecycleOwner) {
            binding.apply {
                Log.d("InfoAutoFragment", "Created")
                brandEditView.text = autoViewModel.currentAutoBrandName?.getEditable()
                modelEditView.text = autoViewModel.currentAutoModelName?.getEditable()
                fuelEditView.text = autoViewModel.currentAutoFuelTypeName?.getEditable()
                registrationEditView.text = autoViewModel.currentAutoRegNumber?.getEditable()
                settingImageView.setOnClickListener {
                    findNavController().navigate(R.id.action_infoAutoFragment_to_settingsFragment)
                }
                continueButton.setOnClickListener {
                    autoViewModel.setNewModel(modelEditView.text.toString())
                    autoViewModel.setNewBrand(brandEditView.text.toString())
                    autoViewModel.setNewFuelType(fuelEditView.text.toString())
                    autoViewModel.setNewRegistrationCertificate(registrationEditView.text.toString())
                    findNavController().navigate(R.id.action_infoAutoFragment_to_chooseAutoFragment2)
                }
            }
        }


//            brandEditView.setOnFocusChangeListener { v, hasFocus ->
//                if (!hasFocus) autoViewModel.setNewBrand(brandEditView.text.toString())
//            }
//            modelEditView.setOnFocusChangeListener { v, hasFocus ->
//                if (!hasFocus) autoViewModel.setNewModel(modelEditView.text.toString())
//            }
//            fuelEditView.setOnFocusChangeListener { v, hasFocus ->
//                if (!hasFocus) autoViewModel.setNewFuelType(fuelEditView.text.toString())
//            }
//    }
    }

//    private fun getEditableFromText(text: String): Editable =
//        Editable.Factory.getInstance().newEditable(text)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}