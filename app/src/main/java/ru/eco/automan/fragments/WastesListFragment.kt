package ru.eco.automan.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.adapters.ExpenseCategoryAdapter
import ru.eco.automan.databinding.FragmentWastesAutoBinding
import ru.eco.automan.listeners.OnAddExpenseListener
import ru.eco.automan.listeners.OnChangeExpenseListener
import ru.eco.automan.viewModelFactories.AutoViewModelFactory
import ru.eco.automan.viewModelFactories.ExpenseViewModelFactory
import ru.eco.automan.viewModels.AutoViewModel
import ru.eco.automan.viewModels.ExpenseViewModel

class WastesListFragment : Fragment(R.layout.fragment_wastes_auto), OnAddExpenseListener, OnChangeExpenseListener {
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
        val eca = ExpenseCategoryAdapter(data, this, this)

        val lastPeriod = requireContext().resources.getStringArray(R.array.wastes_periods).lastIndex
        binding.apply {
            expensesRecycler.layoutManager = LinearLayoutManager(view.context)
            periodSpinner.setSelection(lastPeriod)
            expensesRecycler.adapter = eca
            periodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {
                    onUpdatePeriodSpinner(position, eca, lastPeriod)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

            addCategory.setOnClickListener {
                if (newCategory.text.toString().isNotEmpty())
                    expenseViewModel.addNewCategory(newCategory.text.toString())
                newCategory.text = "".getEditable()
            }
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


//        expenseViewModel.categories.observe(viewLifecycleOwner) {
//            val data = expenseViewModel.getCategoryWithExpensesAndIcon(view.context)
//            val eca = ExpenseCategoryAdapter(data, this)
//            binding.expensesRecycler.adapter = eca
//            eca.notifyDataSetChanged()
//        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun onUpdatePeriodSpinner(position: Int, eca: ExpenseCategoryAdapter, lastPeriod: Int) {
        eca.updateData(expenseViewModel.getExpensesByPeriod(position, lastPeriod, requireContext()))
        eca.notifyDataSetChanged()
    }

    override fun addExpense(categoryName: String, expenseName: String, expenseAmount: Float) {
        expenseViewModel.addNewExpense(
            name = expenseName,
            categoryName = categoryName,
            amount = expenseAmount
        )
    }

    override fun editExpense(expenseId: Int, newExpenseName: String, newExpenseAmount: Float) {
        expenseViewModel.editExpense(expenseId, newExpenseName, newExpenseAmount)
    }

    override fun deleteExpense(expenseId: Int) {
       expenseViewModel.deleteExpense(expenseId)
    }
}