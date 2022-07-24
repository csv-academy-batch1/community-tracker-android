package com.softvision.communitytrackerandroid.data.api

import com.softvision.communitytrackerandroid.data.model.Communities
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.data.model.CommunityRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
     @POST("/community")
     suspend fun addCommunity(@Body community: CommunityRequest): Response<Community>

     @PUT("/community/{id}")
     suspend fun updateCommunity(@Path("id") id:Int, @Body community: Community): Response<Community>

     @GET("/community")
     suspend fun getCommunities(): Response<Communities>
}