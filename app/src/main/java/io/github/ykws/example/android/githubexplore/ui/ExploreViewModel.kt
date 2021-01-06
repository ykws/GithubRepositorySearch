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

  fun search(user: String) {
    viewModelScope.launch {
      _repositories.value = repository.findByUser(user)
    }
  }
}