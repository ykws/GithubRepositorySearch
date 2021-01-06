package io.github.ykws.example.android.githubexplore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    binding.button.setOnClickListener { viewModel.search("ykws") }
  }
}