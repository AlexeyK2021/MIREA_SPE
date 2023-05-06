package ru.eco.automan.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.adapters.EventsAdapter
import ru.eco.automan.databinding.FragmentEventAutoBinding
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModelFactories.EventsViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel
import ru.eco.automan.viewModels.EventsViewModel
import java.sql.Date
import java.util.Calendar

class EventsFragment : Fragment(R.layout.fragment_event_auto) {
    private val eventsViewModel: EventsViewModel by activityViewModels {
        EventsViewModelFactory(AutoApplication.eventsRepository)
    }
    private val autoViewModel: AutoViewModel by activityViewModels {
        AutoViewModelFactory(
            AutoApplication.autoRepository
        )
    }

    private var _binding: FragmentEventAutoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventAutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lm = LinearLayoutManager(this.context)
        var date: Date? = null

        binding.apply {
            eventsList.layoutManager = lm

            eventsViewModel.autoEvents.observe(viewLifecycleOwner) {
                eventsList.adapter =
                    EventsAdapter(eventsViewModel.getEventsByAutoId(autoViewModel.currAuto.value!!.id))
            }

            addEvent.setOnClickListener {
                if (date != null && Calendar.getInstance().timeInMillis < date!!.time)
                    eventsViewModel.addEvent(
                        newEvent.text.toString(),
                        date!!,
                        autoViewModel.currAuto.value!!.id
                    )
                newEvent.text = "".getEditable()
                eventDate.text = ""
            }

            eventDate.setOnClickListener {
                val dateAndTime = Calendar.getInstance()
                val _year = dateAndTime.get(Calendar.YEAR)
                val _month = dateAndTime.get(Calendar.MONTH)
                val _day = dateAndTime.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(requireContext(), { view, year, month, day ->
                    val text = "$year-${month + 1}-$day"
                    date = Date.valueOf(text)
                    val dateToShow = "$day.${month + 1}.${year}"
                    eventDate.text = dateToShow
                }, _year, _month, _day).show()
            }
        }
    }
}