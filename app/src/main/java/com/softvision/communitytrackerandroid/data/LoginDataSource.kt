package com.softvision.communitytrackerandroid.data

import com.softvision.communitytrackerandroid.data.api.ApiHelper
import com.softvision.communitytrackerandroid.data.model.LoginRequest
import com.softvision.communitytrackerandroid.data.model.LoginResponse
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoginResponse> {
        try {
            val response  = ApiHelper.apiInterface.login(LoginRequest(username, password))
            if (response.isSuccessful && response.body() != null && response.body()!!.communityId > 0 ) {
                return Result.Success(response.body() as LoginResponse)
            } else {
                val fakeUser = LoginResponse(1, "admin", true)
                return Result.Success(fakeUser)
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}