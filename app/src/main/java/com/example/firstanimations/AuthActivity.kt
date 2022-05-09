package com.example.firstanimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import com.example.firstanimations.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animations()
        clicks()
    }



    private fun clicks() {
    }

    private fun animations() {
        binding.btnSignIn.startAnimation(AnimationUtils.loadAnimation(this,R.anim.auth_animations))
        binding.btnSignUp.startAnimation(AnimationUtils.loadAnimation(this,R.anim.auth_animations))
        binding.btnSignUp2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.auth_animations))
        binding.btnSignUp3.startAnimation(AnimationUtils.loadAnimation(this,R.anim.auth_animations))
        binding.imgLogo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.auth_animations))
        binding.userNameTIL.startAnimation(AnimationUtils.loadAnimation(this,R.anim.auth_animations))
        binding.passwordTIL.startAnimation(AnimationUtils.loadAnimation(this,R.anim.auth_animations))
    }
}