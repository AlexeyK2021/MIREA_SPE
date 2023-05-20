package ru.eco.automan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    ): View {
        _binding = FragmentAddAutoBinding.inflate(inflater, container, false)
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
        binding.apply {
//            autoPhoto.setOnClickListener {
//                val i = Intent()
//                i.type = "image/*"
//                i.action = Intent.ACTION_GET_CONTENT
//                resultLauncher.launch(i)
//            }
            continueButton.setOnClickListener {
                /* Проверка на пользовательский ввод
                 * Поля отмеченные двумя звёздочками (**) обязательны для ввода
                 */
                val brand = brandEditView.text.toString()               // **
                val model = modelEditView.text.toString()               // **
                val manufactureYear = yearsEditView.text.toString()     // **
                val fuelType = fuelEditView.text.toString()             // **
                val registrationCertificateNumber = insuranceEditView.text.toString()

                when {
                    brand.trim().isEmpty() -> Toast.makeText(view.context, "Поле \"Марка\" не должно быть пустым", Toast.LENGTH_LONG).show()
                    model.trim().isEmpty() -> Toast.makeText(view.context, "Поле \"Модель\" не должно быть пустым", Toast.LENGTH_LONG).show()
                    manufactureYear.trim().isEmpty() -> Toast.makeText(view.context, "Поле \"Год выпуска\" не должно быть пустым", Toast.LENGTH_LONG).show()
                    fuelType.trim().isEmpty() -> Toast.makeText(view.context, "Поле \"Вид топлива\" не должно быть пустым", Toast.LENGTH_LONG).show()
                    else -> {
                        autoViewModel.addNewAuto(
                            brand = brand,
                            model = model,
                            manufactureYear = manufactureYear.toInt(),
                            fuelType = fuelType,
                            registrationCertificateNumber = registrationCertificateNumber
                        )
                        findNavController().navigate(R.id.action_addAutoFragment2_to_chooseAutoFragment2)
                    }
                }

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