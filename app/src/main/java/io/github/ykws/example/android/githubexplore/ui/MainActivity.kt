package io.github.ykws.example.android.githubexplore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import io.github.ykws.example.android.githubexplore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: ExploreViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel = ViewModelProvider(this).get(ExploreViewModel::class.java)
    viewModel.repositories.observe(this) {
      binding.textView.text = it.stream().findFirst().get().name
    }

    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.search(it) }
        return false
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return false
      }
    })
  }
}