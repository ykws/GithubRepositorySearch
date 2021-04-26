package io.github.ykws.example.android.githubexplore.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
  @GET("/search/repositories")
  suspend fun searchRepositories(
    @Query("q") query: String,
    @Query("page") page: Int
  ): Response<SearchRepositoriesResult>
}

data class SearchRepositoriesResult(
  val totalCount: Int,
  val incompleteResults: Boolean,
  val items: List<Repository>
)

data class Repository(
  val id: Int,
  val name: String,
  val description: String,
  val language: String
)
