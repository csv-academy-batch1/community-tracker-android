package com.softvision.communitytrackerandroid.data.api


import com.softvision.communitytrackerandroid.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
     @POST("/community")
     suspend fun addCommunity(@Body community: CommunityRequest): Response<Community>

     @PUT("/community/{id}")
     suspend fun updateCommunity(@Path("id") id: Long, @Body community: Community): Response<Community>

     @GET("/community")
     suspend fun getCommunities(): Response<Communities>

     @POST("/login")
     suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}