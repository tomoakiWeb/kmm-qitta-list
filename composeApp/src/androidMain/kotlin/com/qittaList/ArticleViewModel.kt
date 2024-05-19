package com.qittaList.com.qittaList

import Article
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ArticleViewModel(private val sdk: QittaSDK) : ViewModel() {
    private val _state = mutableStateOf(ArticleScreenState())
    val state: State<ArticleScreenState> = _state
    init {
        loadArticles()
    }
    fun loadArticles() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, articles = emptyList())
            try {
                val articles = sdk.getArticles()
                _state.value = _state.value.copy(isLoading = false, articles = articles)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, articles = emptyList())
            }
        }
    }
}

data class ArticleScreenState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList()
)