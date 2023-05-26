package ru.eco.automan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.databinding.FragmentSettingsAutoBinding
import ru.eco.automan.listeners.OnDialogListener
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModelFactories.EventsViewModelFactory
import ru.eco.automan.viewModelFactories.ExpenseViewModelFactory
import ru.eco.automan.viewModelFactories.SettingsViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel
import ru.eco.automan.viewModels.EventsViewModel
import ru.eco.automan.viewModels.ExpenseViewModel
import ru.eco.automan.viewModels.SettingsViewModel

class SettingsFragment : Fragment(R.layout.fragment_settings_auto), OnDialogListener {
    private val settingsViewModel: SettingsViewModel by activityViewModels {
        SettingsViewModelFactory(AutoApplication.sharedPreferences)
    }
    private val eventsViewModel: EventsViewModel by activityViewModels {
        EventsViewModelFactory(AutoApplication.eventsRepository)
    }
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

    private var _binding: FragmentSettingsAutoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsAutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
//            val themeAdapter = ArrayAdapter.createFromResource(
//                view.context,
//                R.array.themes,
//                R.layout.theme_recycle_item
//            )
//            val themeAdapter = ArrayAdapter<String>(
//                view.context,
//                R.layout.theme_recycle_item,
//                R.id.theme_item_text,
//                resources.getStringArray(R.array.themes)
//            )
//            themeSpinner.adapter = themeAdapter
            themeSpinner.setSelection(0)
//            themeSpinner.onItemSelectedListener = object : OnItemSelectedListener {
//                override fun onItemSelected(
//                    parentView: AdapterView<*>?,
//                    selectedItemView: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    settingsViewModel.setTheme(position)
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {}
//            }
            languageSpinner.setSelection(0)
//            languageSpinner.onItemSelectedListener = object : OnItemSelectedListener {
//                override fun onItemSelected(
//                    parentView: AdapterView<*>?,
//                    selectedItemView: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    settingsViewModel.setLanguage(position)
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {}
//            }
            notificationSwitch.isChecked = settingsViewModel.getNotificationStatus()
            notificationSwitch.setOnCheckedChangeListener { compoundButton, b ->
                settingsViewModel.setNotifications(b)
            }
            eventTextView.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_eventsFragment)
            }
            clearExpenses.setOnClickListener {
                DeleteDialogFragment(
                    R.string.delete_expense_label,
                    R.string.delete_expenses,
                    this@SettingsFragment,
                    this@SettingsFragment.requireContext()
                )
            }
            eventsViewModel.autoEvents.observe(viewLifecycleOwner) {
                eventsNumber.text =
                    eventsViewModel.getEventsByAutoId(autoViewModel.currAuto.value!!.id).size.toString()
            }
        }
    }

    override fun onNegativeButtonClicked() {
    }

    override fun onPositiveButtonClicked() {
        expenseViewModel.deleteAllExpensesByAutoId(autoViewModel.currAuto.value!!.id)
        expenseViewModel.deleteAllCategoriesByAutoId(autoViewModel.currAuto.value!!.id)
    }
}