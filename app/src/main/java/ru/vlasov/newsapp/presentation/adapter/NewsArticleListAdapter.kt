package ru.vlasov.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.vlasov.newsapp.databinding.ItemNewsArticleBinding
import ru.vlasov.newsapp.domain.NewsArticle


class NewsArticleListAdapter()
    : ListAdapter<NewsArticle, NewsArticleViewHolder>(NewsArticleDiffCallback()) {

    var onItemClickListener: ((NewsArticle) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val binding = ItemNewsArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val newsArticle = getItem(position)
        val options = RequestOptions().fitCenter().centerCrop()
        with(holder.binding) {
            Glide.with(holder.itemView).load(newsArticle.urlToImage).apply(options).into(ivArticleImage)
            tvTitle.text = newsArticle.title

            root.setOnClickListener {
                onItemClickListener?.let{ it(newsArticle) }
            }
        }
    }
}