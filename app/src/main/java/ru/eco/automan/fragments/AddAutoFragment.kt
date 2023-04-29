package ru.eco.automan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.databinding.FragmentAddAutoBinding
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel


class AddAutoFragment : Fragment(R.layout.fragment_add_auto) {
    private val autoViewModel: AutoViewModel by activityViewModels {
        AutoViewModelFactory(
            AutoApplication.autoRepository
        )
    }
    private var _binding: FragmentAddAutoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAutoBinding.inflate(inflater, container, false)
//        Log.d("AddAutoFragment", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val resultLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (result.resultCode == Activity.RESULT_OK) {
//                    val data: Intent? = result.data
//
//                }
//            }
        autoViewModel.createNewAuto()
        val brands = autoViewModel.getBrandsNamesList()
        var models = listOf<String>()
        val fuelTypes = autoViewModel.fuelTypes
        binding.apply {
//            autoPhoto.setOnClickListener {
//                val i = Intent()
//                i.type = "image/*"
//                i.action = Intent.ACTION_GET_CONTENT
//                resultLauncher.launch(i)
//            }
            brandEditView.adapter = ArrayAdapter(
                view.context,
                androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                brands
            )
            brandEditView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {
                    autoViewModel.setNewAutoBrandName(brands[position])

                    models = autoViewModel.getModelsNamesList()
                    modelEditView.adapter = ArrayAdapter(
                        view.context,
                        androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                        models
                    )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

            modelEditView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {
                    autoViewModel.setNewAutoModelName(models[position])
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

            fuelEditView.adapter = ArrayAdapter(
                view.context,
                androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                fuelTypes
            )
            fuelEditView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {
                    autoViewModel.setNewAutoFuelTypeName(fuelTypes[position].name)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

            continueButton.setOnClickListener {
//                autoViewModel.addNewAuto(
//                    brand = brandEditView.text.toString(),
//                    model = modelEditView.text.toString(),
//                    manufactureYear = yearsEditView.text.toString().toInt(),
//                    fuelType = fuelEditView.text.toString(),
//                    registrationCertificateNumber = insuranceEditView.text.toString()
//                )
                autoViewModel.setNewAutoManufactureYear(yearsEditView.text.toString().toInt())
                autoViewModel.setNewAutoRegNumber(insuranceEditView.text.toString())
                autoViewModel.buildAuto()
                findNavController().navigate(R.id.action_addAutoFragment2_to_chooseAutoFragment2)
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