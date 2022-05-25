package com.example.firstanimations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.AppDatabase
import com.example.firstanimations.data.models.UserEntity
import com.example.firstanimations.databinding.ActivityRegisterBinding
import com.example.firstanimations.presentation.UserViewModel
import com.example.firstanimations.presentation.UserViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<UserViewModel> {
        UserViewModelFactory(
            AppDatabase.getUserDatabase(
                applicationContext
            ).UserDao()
        )
    }
    private val isChecked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        clicks()


    }

    private fun clicks() {
        binding.btnSignUp.setOnClickListener { validate() }
    }

    private fun validate() {
        val results =
            arrayOf(validateName(), validateLastName(), validateEmail(), validatePassword())
        if (false in results) {
            return
        }
        saveUser()
    }

    private fun saveUser() {
        var profile = ""
        if (binding.checkBox.isChecked) {
            profile = "Admin"
        } else {
            profile = "User"
        }
        lifecycleScope.launchWhenStarted {
            viewModel.saveUser(
                UserEntity(
                    0,
                    binding.nameEdt.text.toString(),
                    binding.emailEdt.text.toString(),
                    binding.lastNameEdt.text.toString(),
                    binding.passwordEdt.text.toString(),
                    profile,
                    false
                )
            ).collect {
                when (it) {
                    is Result.Success -> {
                        Log.e("saveUser: ", it.data.toString())
                        val i = Intent(this@RegisterActivity, AdminActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                    is Result.Failure -> {
                        Snackbar.make(binding.root, "You can't register", Snackbar.LENGTH_SHORT)
                            .show()
                        Log.e("saveUser: ", it.exception.message.toString())
                    }
                }
            }
        }
    }

    private fun validateName(): Boolean {
        return if (binding.nameEdt.text.toString().isNullOrEmpty()) {
            binding.nameTIL.error = "This field can't be empty"
            false
        } else if (binding.nameEdt.text.toString().length < 3) {
            binding.nameTIL.error = "This field have to need more than 3 characters"
            false
        } else {
            binding.nameTIL.error = null
            true
        }
    }

    private fun validateLastName(): Boolean {
        return if (binding.lastNameEdt.text.toString().isNullOrEmpty()) {
            binding.lastNameTIL.error = "This field can't be empty"
            false
        } else if (binding.lastNameEdt.text.toString().length < 3) {
            binding.lastNameTIL.error = "This field have to need more than 3 characters"
            false
        } else {
            binding.lastNameTIL.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val emailRegex2 = Pattern.compile(
            "^([a-zA-Z]{1,1}[a-zA-z0-9]{3,}[._-]{1,1}[a-zA-Z0-9]{4,}@[a-z]{3,}[.]{1,1}[a-z]{2,})"
        )
        val emailRegex3 = Pattern.compile(
            "^([a-zA-Z]{1,1}[a-zA-Z0-9]{7,}@[a-z]{3,}[.]{1,1}[a-z]{2,})"
        )
        return if (binding.emailEdt.text.toString().isNullOrEmpty()) {
            binding.emailTIL.error = "This field can't be empty"
            false
        } else if (!emailRegex2.matcher(binding.emailEdt.text.toString()).matches() && !emailRegex3.matcher(binding.emailEdt.text.toString()).matches() ) {
            binding.emailTIL.error = "The email is wrong with the established guidelines"
            false
        } else {
            binding.emailTIL.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        return if (binding.passwordEdt.text.toString().isNullOrEmpty()) {
            binding.passwordTIL.error = "This field can't be empty"
            false
        } else if (binding.passwordEdt.text.toString().length < 3) {
            binding.passwordTIL.error = "This field have to need more than 3 characters"
            false
        } else {
            binding.passwordTIL.error = null
            true
        }
    }
}