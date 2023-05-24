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
import ru.eco.automan.databinding.FragmentMainBinding
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModelFactories.ExpenseViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel
import ru.eco.automan.viewModels.ExpenseViewModel

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
        binding.apply {
            settingsButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            }

            editAutoButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_infoAutoFragment)
            }

            moreExpencesImage.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_wastesListFragment)
            }

            finesButton.setOnClickListener {
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

            pddButton.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_rulesChapterFragment)
            }

            autoImage.setOnClickListener {
                AutoApplication.notificationManager.createTestNotification(view.context)
            }
            expenseViewModel.userExpenses.observe(viewLifecycleOwner) {
                oilBar.progress = expenseViewModel.getPercentageWastesByName(
                    "Топливо",
                    autoViewModel.currAuto.value!!.id,
                    requireContext().resources.getInteger(R.integer.max_oil_wastes_sum)
                )

                otherWastesBar.progress = expenseViewModel.getPercentageOtherWastes(
                    autoViewModel.currAuto.value!!.id,
                    requireContext()
                )

                repairBar.progress = expenseViewModel.getPercentageWastesByName(
                    "Ремонт",
                    autoViewModel.currAuto.value!!.id,
                    requireContext().resources.getInteger(R.integer.max_repairing_wastes_sum)
                )
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