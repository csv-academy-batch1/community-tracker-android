package com.softvision.communitytrackerandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.softvision.communitytrackerandroid.data.repository.LoginRepository
import com.softvision.communitytrackerandroid.data.Result

import com.softvision.communitytrackerandroid.ui.login.state.LoginFormState
import com.softvision.communitytrackerandroid.ui.login.state.LoginResult

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginUsernameForm = MutableLiveData<LoginFormState>()
    val loginUsernameFormState: LiveData<LoginFormState> = _loginUsernameForm

    private val _loginPasswordForm = MutableLiveData<LoginFormState>()
    val loginPasswordForm: LiveData<LoginFormState> = _loginPasswordForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    suspend fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(loggedInUser = result.data)
        } else {
            _loginResult.value = LoginResult(isError = true)
        }
    }

    fun usernameDataChanged(username: String) {
        if (!isUserNameValid(username)) {
            _loginUsernameForm.value = LoginFormState(isUsernameValid = false)
        }
    }

    fun passwordDataChanged(password: String) {
        if (!isPasswordValid(password)) {
            _loginPasswordForm.value = LoginFormState(isPasswordValid = false)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(isUsernameValid = false)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(isPasswordValid = false)
        } else {
            _loginForm.value = LoginFormState(isUsernameValid = true, isPasswordValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotEmpty()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}