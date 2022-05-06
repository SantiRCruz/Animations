package com.example.firstanimations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.firstanimations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animations()
        clicks()
    }

    private fun clicks() {
        binding.btnNextCourse.setOnClickListener {
            val i  = Intent(this@MainActivity,MainActivity2::class.java)
            startActivity(i)
        }
    }

    private fun animations() {
        val ttb = AnimationUtils.loadAnimation(this,R.anim.ttb)
        binding.headertitle.startAnimation(ttb)
        binding.subtitle.startAnimation(ttb)
        binding.icCards.startAnimation(AnimationUtils.loadAnimation(this,R.anim.stb))
        binding.resultOne.startAnimation(AnimationUtils.loadAnimation(this,R.anim.btt))
        binding.resultTwo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.btt2))
        binding.resultThree.startAnimation(AnimationUtils.loadAnimation(this,R.anim.btt3))
        binding.btnNextCourse.startAnimation(AnimationUtils.loadAnimation(this,R.anim.btn_course))
    }
}