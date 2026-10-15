package com.lmsgov.school.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmsgov.school.data.model.Notice
import com.lmsgov.school.data.repository.LmsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoticesViewModel : ViewModel() {
    private val repository = LmsRepository()

    var notices by mutableStateOf<List<Notice>>(emptyList())
        private set
    var category by mutableStateOf("All")
        private set
    var searchQuery by mutableStateOf("")
        private set
    var selectedNotice by mutableStateOf<Notice?>(null)
        private set
    var isLoading by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            delay(80)
            notices = repository.getNotices()
            isLoading = false
        }
    }

    fun updateCategory(cat: String) {
        category = cat
        viewModelScope.launch {
            isLoading = true
            notices = repository.getNotices(cat)
            isLoading = false
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun updateSelectedNotice(notice: Notice?) {
        selectedNotice = notice
    }

    val filteredNotices: List<Notice>
        get() {
            if (searchQuery.isBlank()) return notices
            return notices.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                    it.summary.contains(searchQuery, ignoreCase = true)
            }
        }
}
