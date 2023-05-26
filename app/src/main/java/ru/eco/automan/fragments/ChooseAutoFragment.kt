package ru.eco.automan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.adapters.ChooseAutoAdapter
import ru.eco.automan.databinding.FragmentControlAutoBinding
import ru.eco.automan.listeners.OnAutoChooseClickListener
import ru.eco.automan.listeners.OnDialogListener
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModelFactories.EventsViewModelFactory
import ru.eco.automan.viewModelFactories.ExpenseViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel
import ru.eco.automan.viewModels.EventsViewModel
import ru.eco.automan.viewModels.ExpenseViewModel

class ChooseAutoFragment : Fragment(R.layout.fragment_control_auto), OnAutoChooseClickListener,
    OnDialogListener {
    private val autoViewModel: AutoViewModel by activityViewModels {
        AutoViewModelFactory(
            AutoApplication.autoRepository
        )
    }
    private val expenseViewModel: ExpenseViewModel by activityViewModels {
        ExpenseViewModelFactory(
            AutoApplication.expenseRepository
        )
    }
    private val eventsViewModel: EventsViewModel by activityViewModels {
        EventsViewModelFactory(AutoApplication.eventsRepository)
    }

    private var _binding: FragmentControlAutoBinding? = null
    private val binding get() = _binding!!

    private var deleteAutoId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlAutoBinding.inflate(inflater, container, false)
//        Log.d("AddAutoFragment", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userAutosList.layoutManager = LinearLayoutManager(view.context)
        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_chooseAutoFragment2_to_addAutoFragment2)
        }

        autoViewModel.userAutos.observe(viewLifecycleOwner) {
            val autos = autoViewModel.getAutosWithBrandAndModel()
            if (autos.isEmpty()) binding.mainTextView.text = getText(R.string.add_your_auto)
            else binding.mainTextView.text = getText(R.string.choose_your_auto)

            binding.userAutosList.adapter = ChooseAutoAdapter(autos, this)
        }
    }

    override fun onDeleteClick(autoId: Int) {
        DeleteDialogFragment(
            R.string.delete_auto_label,
            R.string.delete_auto,
            this,
            requireContext()
        )
        deleteAutoId = autoId
    }

    override fun onChooseClick(autoId: Int) {
        autoViewModel.setCurrentAutoById(autoId)
        findNavController().navigate(R.id.action_chooseAutoFragment2_to_mainFragment)
    }

    override fun onNegativeButtonClicked() {
        deleteAutoId = 0
    }

    override fun onPositiveButtonClicked() {
        expenseViewModel.deleteAllExpensesByAutoId(deleteAutoId)
        eventsViewModel.deleteEventsByAutoId(deleteAutoId)
        autoViewModel.deleteAuto(deleteAutoId)
    }
}