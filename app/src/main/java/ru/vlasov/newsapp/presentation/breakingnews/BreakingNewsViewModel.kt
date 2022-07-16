package ru.vlasov.newsapp.presentation.breakingnews

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
class BreakingNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _breakingNews = MutableLiveData<Resource<NewsResponse>>()
    val breakingNews = _breakingNews

    init {
        getBreakingNews()
    }

    private fun getBreakingNews() {
        viewModelScope.launch {
            newsRepository.getBreakingNews("ru", 1).collect {
                _breakingNews.value = it
            }
        }
    }
}