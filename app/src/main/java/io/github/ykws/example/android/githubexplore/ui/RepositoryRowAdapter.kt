package io.github.ykws.example.android.githubexplore.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.ykws.example.android.githubexplore.R
import io.github.ykws.example.android.githubexplore.data.Repository

class RepositoryRowAdapter(private val onClick: (Repository) -> Unit) :
  ListAdapter<Repository, RepositoryRowAdapter.RepositoryViewHolder>(RepositoryDiffCallback) {

  class RepositoryViewHolder(view: View, val onClick: (Repository) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val textView: TextView = view.findViewById(R.id.name)
    private var currentRepository: Repository? = null

    init {
      view.setOnClickListener {
        currentRepository?.let {
          onClick(it)
        }
      }
    }

    fun bind(repository: Repository) {
      currentRepository = repository
      textView.text = repository.name
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.repository_row_item, parent, false)
    return RepositoryViewHolder(view, onClick)
  }

  override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
    val repository = getItem(position)
    holder.bind(repository)
  }
}

object RepositoryDiffCallback : DiffUtil.ItemCallback<Repository>() {
  override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
    return oldItem.id == newItem.id
  }
}
