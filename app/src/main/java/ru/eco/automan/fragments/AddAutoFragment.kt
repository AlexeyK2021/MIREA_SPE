package ru.eco.automan.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
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
        binding.apply {
//            autoPhoto.setOnClickListener {
//                val i = Intent()
//                i.type = "image/*"
//                i.action = Intent.ACTION_GET_CONTENT
//                resultLauncher.launch(i)
//            }
            continueButton.setOnClickListener {
                autoViewModel.addNewAuto(
                    brand = brandEditView.text.toString(),
                    model = modelEditView.text.toString(),
                    manufactureYear = yearsEditView.text.toString().toInt(),
                    fuelType = fuelEditView.text.toString(),
                    registrationCertificateNumber = insuranceEditView.text.toString()
                )
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