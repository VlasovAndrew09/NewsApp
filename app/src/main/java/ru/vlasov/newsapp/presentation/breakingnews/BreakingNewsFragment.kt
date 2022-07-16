package ru.vlasov.newsapp.presentation.breakingnews

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
import ru.vlasov.newsapp.domain.util.Resource
import ru.vlasov.newsapp.presentation.adapter.NewsArticleListAdapter

@AndroidEntryPoint
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private val viewModel: BreakingNewsViewModel by viewModels()

    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding: FragmentBreakingNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentBreakingNewsBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
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
                R.id.action_breakingNewsFragment_to_newsArticleFragment,
                bundle
            )
        }
        binding.rvBreakingNews.adapter = adapter
        binding.rvBreakingNews.layoutManager = LinearLayoutManager(activity)


        viewModel.breakingNews.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Success -> {
                    binding.paginationProgressBar.visibility = View.GONE

                    adapter.submitList(response.data?.result)
                }
                is Resource.Error -> {
                    binding.paginationProgressBar.visibility = View.GONE
                    response.message?.let {
                        showError(it)
                    }
                }
                is Resource.Loading -> {
                    binding.paginationProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showError(msg: String) {
        view?.let {
            Snackbar.make(it, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
            }.show()
        }
    }
}