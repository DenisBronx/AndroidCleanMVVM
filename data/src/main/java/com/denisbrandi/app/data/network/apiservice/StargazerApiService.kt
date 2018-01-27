package com.denisbrandi.app.data.network.apiservice

import com.denisbrandi.app.domain.model.Stargazer
import io.reactivex.Observable
import retrofit2.http.Path
import retrofit2.http.Query


interface StargazerApiService {

    fun getStargazers(@Path("owner") owner: String,
                      @Path("repo") repo: String,
                      @Query("page") page: Int): Observable<List<Stargazer>>

}