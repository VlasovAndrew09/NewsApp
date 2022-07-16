package ru.vlasov.newsapp.presentation.savednews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.vlasov.newsapp.R
import ru.vlasov.newsapp.databinding.FragmentBreakingNewsBinding
import ru.vlasov.newsapp.databinding.FragmentSavedNewsBinding
import ru.vlasov.newsapp.domain.util.Resource
import ru.vlasov.newsapp.presentation.adapter.NewsArticleListAdapter
import ru.vlasov.newsapp.presentation.breakingnews.BreakingNewsViewModel

@AndroidEntryPoint
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private val viewModel: SavedNewsViewModel by viewModels()

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding: FragmentSavedNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentSavedNewsBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsArticleListAdapter()
        adapter.onItemClickListener = {
            val bundle = Bundle().apply {
                putParcelable("newsArticle", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_newsArticleFragment,
                bundle
            )
        }
        binding.rvSavedNews.adapter = adapter
        binding.rvSavedNews.layoutManager = LinearLayoutManager(activity)

        viewModel.savedNews.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}