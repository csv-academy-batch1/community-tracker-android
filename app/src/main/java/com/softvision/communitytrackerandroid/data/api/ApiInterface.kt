package com.softvision.communitytrackerandroid.data.api

import com.softvision.communitytrackerandroid.data.Community
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
     @POST("/community")
     suspend fun addCommunity(@Body community: Community): Response<Community>
}