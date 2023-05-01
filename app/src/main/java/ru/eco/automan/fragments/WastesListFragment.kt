package ru.eco.automan.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.adapters.ExpenseAdapter
import ru.eco.automan.adapters.ExpenseCategoryAdapter
import ru.eco.automan.adapters.ExpenseCategoryViewHolder
import ru.eco.automan.databinding.FragmentWastesAutoBinding
import ru.eco.automan.listeners.OnAddExpenseListener
import ru.eco.automan.models.CategoryWithExpenseAndIcon
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModelFactories.ExpenseViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel
import ru.eco.automan.viewModels.ExpenseViewModel

class WastesListFragment : Fragment(R.layout.fragment_wastes_auto), OnAddExpenseListener {
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

    private var _binding: FragmentWastesAutoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWastesAutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenseViewModel.setCurrentAuto(autoViewModel.currAuto.value!!.id)

        val data = expenseViewModel.getCategoryWithExpensesAndIcon(view.context)
        val eca = ExpenseCategoryAdapter(data, this)

        binding.apply {
            expensesRecycler.layoutManager = LinearLayoutManager(view.context)
            periodSpinner.setSelection(requireContext().resources.getStringArray(R.array.wastes_periods).lastIndex)
            expensesRecycler.adapter = eca
//            periodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parentView: AdapterView<*>?,
//                    selectedItemView: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    adapter = createAdapter(expenseViewModel.getExpensesByPeriod(position))
//                    expensesRecycler.adapter = adapter
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {}
//            }
        }

        expenseViewModel.userExpenses.observe(viewLifecycleOwner) {
            Log.d("expenseViewModel.userExpenses", it.toString())
            eca.updateData(expenseViewModel.getCategoryWithExpensesAndIcon(view.context))
            eca.notifyDataSetChanged()
        }
        expenseViewModel.categories.observe(viewLifecycleOwner) {
            Log.d("expenseViewModel.categories", it.toString())
            eca.updateData(expenseViewModel.getCategoryWithExpensesAndIcon(view.context))
            eca.notifyDataSetChanged()
        }

        expenseViewModel.userExpenses.observe(viewLifecycleOwner) {

        }

//        expenseViewModel.categories.observe(viewLifecycleOwner) {
//            val data = expenseViewModel.getCategoryWithExpensesAndIcon(view.context)
//            val eca = ExpenseCategoryAdapter(data, this)
//            binding.expensesRecycler.adapter = eca
//            eca.notifyDataSetChanged()
//        }

    }

    override fun addExpense(categoryName: String, expenseName: String, expenseAmount: Float) {
        expenseViewModel.addNewExpense(
            name = expenseName,
            categoryName = categoryName,
            amount = expenseAmount
        )
    }
}