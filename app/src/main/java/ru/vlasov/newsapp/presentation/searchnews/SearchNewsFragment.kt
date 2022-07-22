package ru.vlasov.newsapp.presentation.searchnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vlasov.newsapp.R
import ru.vlasov.newsapp.databinding.FragmentSearchNewsBinding
import ru.vlasov.newsapp.domain.util.Resource
import ru.vlasov.newsapp.presentation.adapter.NewsArticleListAdapter

@AndroidEntryPoint
class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private val viewModel: SearchNewsViewModel by viewModels()

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding: FragmentSearchNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchNewsBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchNewsBinding.inflate(inflater, container, false)
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
                R.id.action_searchNewsFragment_to_newsArticleFragment,
                bundle
            )
        }
        binding.rvSearchNews.adapter = adapter
        binding.rvSearchNews.layoutManager = LinearLayoutManager(activity)

        var job: Job? = null
        binding.etSearch.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                it?.let {
                    if(it.toString().isNotEmpty()) {
                        viewModel.searchNews(it.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner) { response ->
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