package io.github.ykws.example.android.githubexplore

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GithubRepository {
  suspend fun findByUser(user: String): List<Repository>? =
    Retrofit.Builder()
      .baseUrl("https://api.github.com")
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create(GithubService::class.java)
      .repos(user)
      .body()
}
