package com.example.miniprojecttest.view.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.miniprojecttest.R
import com.example.miniprojecttest.databinding.FragmentLoginBinding
import com.example.miniprojecttest.view.common.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun getUiBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?, view: View) {
    }

    override fun initUiListener() {
        viewBinding?.run {
            loginEdtxPassword.addTextChangedListener {
                viewModel?.doValidatePassword(it.toString())
            }

            loginEdtxEmail.addTextChangedListener {
                viewModel?.doValidateEmail(it.toString())
            }

            loginBtnLogin.setOnClickListener {
                viewModel?.doLogin(
                    loginEdtxEmail.text.toString(),
                    loginEdtxPassword.text.toString()
                )
            }
        }
    }

    override fun initMenu(): Int = 0

    override fun bindViewModel(): LoginViewModel {
        return ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun observeViewModel(viewModel: LoginViewModel) {
        viewModel.errorPasswordLiveData.observe(this, {
            viewBinding?.loginTxtPassword?.error = it
        })

        viewModel.errorEmailLiveData.observe(this, {
            viewBinding?.loginTxtEmail?.error = it
        })

        viewModel.loginResultLiveData.observe(this, {
            if (it == true) {
                if (getController()?.currentDestination?.id == R.id.loginFragment) {
                    getController()?.navigate(R.id.action_loginActivity_to_movieFragment)
                }
            } else {
                Toast.makeText(requireContext(), "Email dan password salah", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
