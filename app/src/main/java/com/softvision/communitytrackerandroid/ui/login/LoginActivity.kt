package com.softvision.communitytrackerandroid.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.softvision.communitytrackerandroid.MainActivity
import com.softvision.communitytrackerandroid.databinding.ActivityLoginBinding

import com.softvision.communitytrackerandroid.R
import com.softvision.communitytrackerandroid.util.afterTextChanged

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        with(binding) {
            loginViewModel.loginButtonFormState.observe(this@LoginActivity, Observer {
                val loginState = it ?: return@Observer

                // disable login button unless both username / password is valid
                btnLogin.isEnabled =
                    loginState.isUsernameValid == true && loginState.isPasswordValid == true
            })

            loginViewModel.loginUsernameFormState.observe(this@LoginActivity, Observer {
                val loginUsernameState = it ?: return@Observer

                if (loginUsernameState.isUsernameValid == false) {
                    etUsername.error = getString(R.string.invalid_username)
                    etUsername.setBackgroundResource(R.drawable.border_error)
                } else {
                    etUsername.setBackgroundResource(R.drawable.border)
                }
            })

            loginViewModel.loginPasswordFormState.observe(this@LoginActivity, Observer {
                val loginPasswordState = it ?: return@Observer

                if (loginPasswordState.isPasswordValid == false) {
                    etPassword.error = getString(R.string.invalid_password)
                    etPassword.setBackgroundResource(R.drawable.border_error)
                } else {
                    etPassword.setBackgroundResource(R.drawable.border)
                }
            })

            loginViewModel.loginResult.observe(this@LoginActivity, Observer {
                val loginResult = it ?: return@Observer

                pgbLoading.visibility = View.GONE
                if (loginResult.error != null) {
                    showLoginFailed(loginResult.error)
                }
                if (loginResult.success != null) {
                    updateUiWithUser(loginResult.success)
                }
            })

            etUsername.afterTextChanged {
                loginViewModel.loginDataChanged(
                    etUsername.text.toString(),
                    etPassword.text.toString()
                )
                loginViewModel.usernameDataChange(etUsername.text.toString())

            }

            etPassword.apply {
                afterTextChanged {
                    loginViewModel.loginDataChanged(
                        etUsername.text.toString(),
                        etPassword.text.toString()
                    )
                    loginViewModel.passwordDataChange(etPassword.text.toString())
                }

                setOnEditorActionListener { _, actionId, _ ->
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE ->
                            lifecycleScope.launchWhenCreated {
                                loginViewModel.login(
                                    etUsername.text.toString(),
                                    etPassword.text.toString()
                                )
                            }

                    }
                    false
                }
                isLongClickable = false
            }

            btnLogin.setOnClickListener {
                pgbLoading.visibility = View.VISIBLE
                lifecycleScope.launchWhenCreated {
                    loginViewModel.login(
                        etUsername.text.toString(),
                        etPassword.text.toString()
                    )
                }

            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience

        setResult(Activity.RESULT_OK)
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

