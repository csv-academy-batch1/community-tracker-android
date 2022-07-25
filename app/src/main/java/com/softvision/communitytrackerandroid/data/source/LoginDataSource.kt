package com.softvision.communitytrackerandroid.data.source

import com.softvision.communitytrackerandroid.data.Result
import com.softvision.communitytrackerandroid.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // val response = ApiHelper.apiInterface.login(username, password)

            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe", java.util.UUID.randomUUID().toString())
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}