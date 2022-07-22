package com.softvision.communitytrackerandroid.data.api

import tayabas.anthony.retrofitsample.data.api.RetrofitClient

class ApiHelper {

    companion object {
        val BASE_URL_JAVA = "https://community-tracker-java.azurewebsites.net/"
        val BASE_URL_DOT_NET = "https://community-tracker-dotnet.azurewebsites.net/"
        var BASE_URL = BASE_URL_JAVA

        var retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
    }
}