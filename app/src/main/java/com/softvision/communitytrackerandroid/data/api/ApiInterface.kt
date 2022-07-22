package com.softvision.communitytrackerandroid.data.api

import com.softvision.communitytrackerandroid.data.model.Communities
import com.softvision.communitytrackerandroid.data.model.Community
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
     @POST("/community")
     suspend fun addCommunity(@Body community: Community): Response<Community>

     @GET("/community")
     suspend fun getCommunities(): Response<Communities>
}