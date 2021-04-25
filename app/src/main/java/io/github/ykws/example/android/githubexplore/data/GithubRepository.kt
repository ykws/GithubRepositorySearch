package io.github.ykws.example.android.githubexplore.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GithubRepository {
  suspend fun findByQuery(query: String, page: Int): SearchRepositoriesResult? =
    Retrofit.Builder()
      .baseUrl("https://api.github.com")
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create(GithubService::class.java)
      .searchRepositories(query, page)
      .body()
}
