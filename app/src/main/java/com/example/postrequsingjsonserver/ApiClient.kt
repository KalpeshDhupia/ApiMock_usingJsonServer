package com.example.postrequsingjsonserver

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiClient {

    @Headers("Content-Type: application/json")
    @POST("/posts")
    fun createPost(@Body dataModel: DataModel): Call<DataModel>

    @GET("posts")
    fun getPosts() : Call<List<Post>>

}