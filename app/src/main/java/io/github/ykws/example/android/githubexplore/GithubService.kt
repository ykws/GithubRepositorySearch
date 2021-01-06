package io.github.ykws.example.android.githubexplore

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
  @GET("/users/{user}/repos")
  suspend fun repos(
    @Path("user") user: String
  ): Response<List<Repository>>
}

data class Repository(
  val id: Int,
  val name: String
)
