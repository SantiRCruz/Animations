package com.example.firstanimations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import com.example.firstanimations.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        animations()
        startTimer()
    }

    private fun startTimer() {
        object : CountDownTimer(3000,1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                val i = Intent(this@MainActivity2,AuthActivity::class.java)
                startActivity(i)
                finish()
            }
        }.start()
    }

    private fun animations() {
        binding.imgWorld.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rtd))
        binding.imgFifa.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rtd))
        binding.imgAstro.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rpc))
    }
}