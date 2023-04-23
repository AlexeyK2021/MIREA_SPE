package ru.eco.automan.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.adapters.ParagraphsAdapter
import ru.eco.automan.adapters.RulesAdapter
import ru.eco.automan.databinding.FragmentRulesBinding
import ru.eco.automan.databinding.FragmentRulesSectionsBinding
import ru.eco.automan.viewModelFactories.RulesViewModelFactory
import ru.eco.automan.viewModels.RulesViewModel

class RulesParagraphsFragment : Fragment(R.layout.fragment_rules_sections) {
    private val rulesViewModel: RulesViewModel by activityViewModels {
        RulesViewModelFactory(
            AutoApplication.rulesRepository
        )
    }

    private var _binding: FragmentRulesSectionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRulesSectionsBinding.inflate(inflater, container, false)
//        Log.d("AddAutoFragment", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rulesViewModel.currentChapterId.observe(viewLifecycleOwner) { chapterId ->
            val toast =
                Toast.makeText(activity, "CLICKED ${chapterId.toString()}", Toast.LENGTH_SHORT)
            toast.show()

            binding.chapterName.text = rulesViewModel.getCurrentChapterName()
            binding.paragraphsRecycler.adapter =
                ParagraphsAdapter(rulesViewModel.getParagraphsForCurrentChapter())
            binding.paragraphsRecycler.layoutManager = LinearLayoutManager(view.context)

//            Log.d(
//                "RulesParagraphsFragment",
//                "Data collected. First par ${rulesViewModel.getParagraphsForCurrentChapter()[0]}"
//            )
        }
    }
}