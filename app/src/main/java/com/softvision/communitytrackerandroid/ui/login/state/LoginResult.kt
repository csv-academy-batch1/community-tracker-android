package com.softvision.communitytrackerandroid.ui.login.state

import com.softvision.communitytrackerandroid.ui.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult (
    val success: LoggedInUserView? = null,
    val error:Int? = null
)