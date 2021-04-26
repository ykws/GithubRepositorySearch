package io.github.ykws.example.android.githubexplore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ykws.example.android.githubexplore.data.GithubRepository
import io.github.ykws.example.android.githubexplore.data.Repository
import kotlinx.coroutines.launch

class ExploreViewModel : ViewModel() {
  private val repository = GithubRepository()

  private val _repositories = MutableLiveData<List<Repository>>()
  val repositories: LiveData<List<Repository>> get() = _repositories

  private var query: String = ""
  private var page: Int = 1
  private var incompleteResults: Boolean = true

  fun search(query: String) {
    this.query = query
    page = 1
    viewModelScope.launch {
      repository.findByQuery(query)?.let {
        incompleteResults = it.incompleteResults
        _repositories.value = it.items
      }
    }
  }

  fun load() {
    if (incompleteResults) return
    page++
    viewModelScope.launch {
      repository.findByQuery(query, page)?.let {
        incompleteResults = it.incompleteResults
        _repositories.value = _repositories.value?.plus(it.items)
      }
    }
  }
}
