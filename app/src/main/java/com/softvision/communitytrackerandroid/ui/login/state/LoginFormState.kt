package com.softvision.communitytrackerandroid.ui.login.state

/**
 * Data validation state of the login form.
 */
data class LoginFormState (
    val isUsernameValid: Boolean? = true,
    val isPasswordValid: Boolean? = true
)