package com.softvision.communitytrackerandroid.ui.login.state

import com.softvision.communitytrackerandroid.data.model.LoggedInUser

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val loggedInUser: LoggedInUser? = null,
    val isError: Boolean? = false
)