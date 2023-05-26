package ru.eco.automan.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.adapters.currency
import ru.eco.automan.databinding.FragmentMainBinding
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModelFactories.ExpenseViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel
import ru.eco.automan.viewModels.ExpenseViewModel
import kotlin.math.roundToInt

/**
 * Фрагмент, отвечающий за главную страницу приложения
 */
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    private val expenseViewModel: ExpenseViewModel by activityViewModels {
        ExpenseViewModelFactory(
            AutoApplication.expenseRepository
        )
    }
    private val autoViewModel: AutoViewModel by activityViewModels {
        AutoViewModelFactory(
            AutoApplication.autoRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currAuto = autoViewModel.currAuto.value!!
        var name = currAuto.name
        if (name == null) name = "${autoViewModel.currentAutoBrandName} ${autoViewModel.currentAutoModelName}"
        binding.apply {
            autoName.text = name

            settingsButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            }

            editAutoButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_infoAutoFragment)
            }

            moreExpencesImage.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_wastesListFragment)
            }

            finesButton.penaltiesButton.setOnClickListener {
                if (isNetworkAvailable()) {
                    findNavController().navigate(R.id.action_mainFragment_to_finesFragment)
                } else {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.no_internet),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            pddButton.pddButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_rulesChapterFragment)
            }

            autoImage.setOnClickListener {
                AutoApplication.notificationManager.createTestNotification(view.context)
            }

            expenseViewModel.userExpenses.observe(viewLifecycleOwner) {
//                val maxOilSum = resources.getFraction(R.fraction.max_oil_wastes_sum, 1, 1)
//                val maxOtherSum = resources.getFraction(R.fraction.max_other_wastes_sum, 1, 1)
//                val maxRepairSum = resources.getFraction(R.fraction.max_repairing_wastes_sum, 1, 1)
                val maxOilSum = 10000f
                val maxOtherSum = 10000f
                val maxRepairSum = 10000f

                val oil = expenseViewModel.getWastesSumById(
                    1, autoViewModel.currAuto.value!!.id
                )
                val other = expenseViewModel.getOtherWastesSum(
                    autoViewModel.currAuto.value!!.id
                )
                val repair = expenseViewModel.getWastesSumById(
                    2, autoViewModel.currAuto.value!!.id
                )

                oilBar.progress = (oil / maxOilSum * 100).roundToInt()
                otherWastesBar.progress = (other / maxOtherSum * 100).roundToInt()
                repairBar.progress = (repair / maxRepairSum * 100).roundToInt()

                val oilText = "${oil.currency()}/${maxOilSum.currency()}"
                oilExpensesSum.text = oilText
                val otherText = "${other.currency()}/${maxOtherSum.currency()}"
                otherExpensesSum.text = otherText
                val repairText = "${repair.currency()}/${maxRepairSum.currency()}"
                repairExpensesSum.text = repairText
            }
        }

    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}