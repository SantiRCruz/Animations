package com.example.firstanimations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.firstanimations.core.Constants
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.AppDatabase
import com.example.firstanimations.data.models.UserEntity
import com.example.firstanimations.databinding.ActivityUpdateUserBinding
import com.example.firstanimations.presentation.UserViewModel
import com.example.firstanimations.presentation.UserViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class UpdateUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateUserBinding
    private val viewModel by viewModels<UserViewModel> {
        UserViewModelFactory(
            AppDatabase.getUserDatabase(
                applicationContext
            ).UserDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        clicks()

    }

    private fun clicks() {
        binding.btnUpdate.setOnClickListener { validate() }
    }

    private fun validate() {
        val results = arrayOf(validateName(), validateLastName(), validateEmail(), validatePassword())
        if (false in results) {
            return
        }
        updateUser()
    }

    private fun updateUser() {
        var profile = ""
        if (binding.checkBox.isChecked) {
            profile = "Admin"
        } else {
            profile = "User"
        }
        lifecycleScope.launchWhenStarted {
            viewModel.updateUser(
                UserEntity(
                    Constants.USER_UPDATE!!.id_user,
                    binding.nameEdt.text.toString(),
                    binding.emailEdt.text.toString(),
                    binding.lastNameEdt.text.toString(),
                    binding.passwordEdt.text.toString(),
                    profile,
                    Constants.USER_UPDATE!!.blocked
                )
            ).collect {
                when (it) {
                    is Result.Success -> {
                        Log.e("updateUser: ", it.data.toString())
                        val i = Intent(this@UpdateUserActivity, AdminActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                    is Result.Failure -> {
                        Snackbar.make(binding.root, "You can't update", Snackbar.LENGTH_SHORT)
                            .show()
                        Log.e("updateUser: ", it.exception.message.toString())
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
    private fun setData() {
        binding.emailEdt.setText(Constants.USER_UPDATE!!.email)
        binding.lastNameEdt.setText(Constants.USER_UPDATE!!.last_name)
        binding.nameEdt.setText(Constants.USER_UPDATE!!.name)
        binding.passwordEdt.setText(Constants.USER_UPDATE!!.password)
        if (Constants.USER_UPDATE!!.profile == "Admin") {
            binding.checkBox.isChecked = true
        }
    }
}