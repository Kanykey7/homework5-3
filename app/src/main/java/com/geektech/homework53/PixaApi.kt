package com.geektech.homework53

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")

    fun getImage(
        @Query("q") keyWord: String,
        @Query("key") key: String = "33194504-975d1bb3845cefc1e42717507",
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = 3,

        ): Call<PixaModel>


}