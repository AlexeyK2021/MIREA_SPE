package ru.eco.automan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.adapters.OnChapterClickListener
import ru.eco.automan.adapters.RulesAdapter
import ru.eco.automan.databinding.FragmentRulesBinding
import ru.eco.automan.viewModelFactories.RulesViewModelFactory
import ru.eco.automan.viewModels.RulesViewModel


class RulesChapterFragment : Fragment(R.layout.fragment_rules), OnChapterClickListener {
    private val rulesViewModel: RulesViewModel by activityViewModels { RulesViewModelFactory(AutoApplication.rulesRepository) }

    private var _binding: FragmentRulesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRulesBinding.inflate(inflater, container, false)
//        Log.d("AddAutoFragment", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rulesRecycler.adapter = RulesAdapter(rulesViewModel.getAllChapters(), this)
        binding.rulesRecycler.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onChapterClick(chapterId: Int) {
        rulesViewModel.currentChapterId.postValue(chapterId)
        findNavController().navigate(R.id.action_rulesChapterFragment_to_rulesParagraphsFragment2)
    }
}