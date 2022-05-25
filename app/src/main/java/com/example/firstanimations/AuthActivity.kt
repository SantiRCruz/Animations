package com.example.firstanimations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.firstanimations.core.Constants
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.AppDatabase
import com.example.firstanimations.data.models.UserEntity
import com.example.firstanimations.databinding.ActivityAuthBinding
import com.example.firstanimations.presentation.UserViewModel
import com.example.firstanimations.presentation.UserViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val viewModel by viewModels<UserViewModel> {
        UserViewModelFactory(
            AppDatabase.getUserDatabase(
                applicationContext
            ).UserDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        animations()
        clicks()
    }

    private fun clicks() {
        binding.btnSignIn.setOnClickListener { signIn() }
        binding.btnSignUp.setOnClickListener { goToSignUp() }

    }

    private fun goToSignUp() {
        val i  = Intent(this@AuthActivity,RegisterActivity::class.java)
        startActivity(i)
    }

    private fun signIn() {
        lifecycleScope.launchWhenStarted {
            viewModel.fetchUser(binding.userNameEdt.text.toString()).collect {
                when (it) {
                    is Result.Success -> {
                        Log.e("signIn: ", it.toString())
                        if (it.data.isNullOrEmpty()) {
                            Snackbar.make(
                                binding.root,
                                "You are not registered in the app",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            binding.btnSignUp.visibility = View.VISIBLE
                        } else {
                            if (!it.data[0].blocked) {
                                if (binding.passwordEdt.text.toString() == it.data[0].password) {
                                    Snackbar.make(binding.root, "well done", Snackbar.LENGTH_SHORT)
                                        .show()
                                    Constants.CURRENT_USER = it.data[0] as UserEntity
                                    if (it.data[0].profile == "Admin") {
                                        val i = Intent(this@AuthActivity, AdminActivity::class.java)
                                        startActivity(i)
                                        finish()
                                    } else {
                                        Snackbar.make(
                                            binding.root,
                                            "you are normal",
                                            Snackbar.LENGTH_SHORT
                                        )
                                    }
                                } else {
                                    Snackbar.make(
                                        binding.root,
                                        "The password is wrong",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "you are blocked",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    is Result.Failure -> {
                        Snackbar.make(binding.root, "Error! ${it.exception}", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }


    private fun animations() {
        binding.btnSignIn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.auth_animations))
        binding.btnSignUp.startAnimation(AnimationUtils.loadAnimation(this, R.anim.auth_animations))
        binding.imgLogo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.auth_animations))
        binding.userNameTIL.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.auth_animations
            )
        )
        binding.passwordTIL.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.auth_animations
            )
        )
    }
}