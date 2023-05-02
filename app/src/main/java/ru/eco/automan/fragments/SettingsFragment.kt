package ru.eco.automan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.eco.automan.R
import ru.eco.automan.databinding.FragmentSettingsAutoBinding
import ru.eco.automan.viewModels.EventsViewModel
import ru.eco.automan.viewModels.SettingsViewModel

class SettingsFragment : Fragment(R.layout.fragment_settings_auto) {
    private val settingsViewModel: SettingsViewModel by activityViewModels()
    private val eventsViewModel:EventsViewModel by activityViewModels()

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
            themeSpinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {
//                    Toast.makeText(
//                        view.context,
//                        "Clicked ${resources.getStringArray(R.array.themes)[position]}",
//                        Toast.LENGTH_LONG
//                    ).show()
                    settingsViewModel.setTheme(position)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
            languageSpinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {
//                    Toast.makeText(
//                        view.context,
//                        "Clicked ${resources.getStringArray(R.array.languages)[position]}",
//                        Toast.LENGTH_LONG
//                    ).show()
                    settingsViewModel.setLanguage(position)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
            notificationSwitch.setOnCheckedChangeListener { compoundButton, b ->
                settingsViewModel.setNotifications(b)
            }

            eventTextView.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_eventsFragment)
            }

            eventsNumber.text = eventsViewModel.getEventsNumber().toString()


        }
    }

}