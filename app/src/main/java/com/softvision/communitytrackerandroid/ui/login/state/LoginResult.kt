package com.softvision.communitytrackerandroid.ui.login.state

import com.softvision.communitytrackerandroid.data.model.LoginResponse

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult (
    val success: LoginResponse? = null,
    val error: Boolean = false
)