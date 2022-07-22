package ru.vlasov.newsapp.presentation.searchnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.vlasov.newsapp.domain.NewsRepository
import ru.vlasov.newsapp.domain.util.NewsResponse
import ru.vlasov.newsapp.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _searchNews = MutableLiveData<Resource<NewsResponse>>()
    val searchNews = _searchNews

    fun searchNews(searchQuery: String) {
        viewModelScope.launch {
            newsRepository.searchNews(searchQuery, 1).collect {
                _searchNews.value = it
            }
        }
    }
}