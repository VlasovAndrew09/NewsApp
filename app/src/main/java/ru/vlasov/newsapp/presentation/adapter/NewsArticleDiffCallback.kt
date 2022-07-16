package ru.vlasov.newsapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.vlasov.newsapp.domain.NewsArticle

class NewsArticleDiffCallback : DiffUtil.ItemCallback<NewsArticle>() {

    override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle) =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle) =
        oldItem == newItem
}