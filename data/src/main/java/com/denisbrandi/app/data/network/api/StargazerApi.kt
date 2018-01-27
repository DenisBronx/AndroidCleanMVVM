package com.denisbrandi.app.data.network.api

import com.denisbrandi.app.data.network.model.NetworkStargazer
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface StargazerApi {

    @GET("repos/{owner}/{repo}/stargazers")
    fun getStargazers(@Path("owner") owner: String,
                      @Path("repo") repo: String,
                      @Query("page") page: Int): Single<List<NetworkStargazer>>

}