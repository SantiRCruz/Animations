package com.example.firstanimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstanimations.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private val isChecked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clicks()


    }

    private fun clicks() {
        binding.btnSignUp.setOnClickListener { validate() }
    }

    private fun validate() {
        val results = arrayOf(validateName(),validateLastName(),validateEmail(),validatePassword())
    }

    private fun validateName(): Boolean {
        return if(binding.NameEdt.text.toString().isNullOrEmpty()){
            binding.NameTIL.error = "This field can't be empty"
            false
        }else if(binding.NameEdt.text.toString().length<3){
            binding.NameTIL.error = "This field have to need more than 3 characters"
            false
        }else{
            binding.NameTIL.error = null
            true
        }
    }
    private fun validateLastName(): Boolean {
        return if(binding.NameEdt.text.toString().isNullOrEmpty()){
            binding.NameTIL.error = "This field can't be empty"
            false
        }else if(binding.NameEdt.text.toString().length<3){
            binding.NameTIL.error = "This field have to need more than 3 characters"
            false
        }else{
            binding.NameTIL.error = null
            true
        }
    }
    private fun validateEmail(): Boolean {
        val emailRegexWithCharacter = Pattern.compile(
            "([._-]{1,1}@)"
        )
        val emailRegex2 = Pattern.compile(
            "^([a-zA-Z]{1,1}[a-zA-z0-9]{3,}[._-]{1,1}[a-zA-Z0-9]{4}@[a-z]{3}.[a-z]{2})"
        )
        val emailRegex3 = Pattern.compile(
            "^([a-zA-Z]{1,1}[a-zA-Z0-9]{7}@[a-z]{3}.[a-z]{2})"
        )

        var regex = Regex(emailRegex3.pattern())

        return if(binding.NameEdt.text.toString().isNullOrEmpty()){
            binding.NameTIL.error = "This field can't be empty"
            false
        }else if(!emailRegexWithCharacter.matcher(binding.emailEdt.text.toString()).matches()){
            if(!emailRegex2.matcher(binding.emailEdt.text.toString()).matches()){
                binding.NameTIL.error = "This field is wrong"
            }
            false
        }else if(!emailRegex3.matcher(binding.emailEdt.text.toString()).matches()){
            binding.NameTIL.error = "This field is wrong"
            false
        } else{
            binding.NameTIL.error = null
            true
        }
    }
    private fun validatePassword(): Boolean {
        return if(binding.NameEdt.text.toString().isNullOrEmpty()){
            binding.NameTIL.error = "This field can't be empty"
            false
        }else if(binding.NameEdt.text.toString().length<3){
            binding.NameTIL.error = "This field have to need more than 3 characters"
            false
        }else{
            binding.NameTIL.error = null
            true
        }
    }
}