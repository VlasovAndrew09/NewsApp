package ru.vlasov.newsapp.presentation.newsarticle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.vlasov.newsapp.R
import ru.vlasov.newsapp.databinding.FragmentBreakingNewsBinding
import ru.vlasov.newsapp.databinding.FragmentNewsArticleBinding
import ru.vlasov.newsapp.presentation.breakingnews.BreakingNewsViewModel

@AndroidEntryPoint
class NewsArticleFragment : Fragment(R.layout.fragment_news_article) {

    private val viewModel: NewsArticleViewModel by viewModels()
    val args: NewsArticleFragmentArgs by navArgs()

    private var _binding: FragmentNewsArticleBinding? = null
    private val binding: FragmentNewsArticleBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsArticleBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsArticle = args.newsArticle
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(newsArticle.url)
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(newsArticle)
        }
    }
}