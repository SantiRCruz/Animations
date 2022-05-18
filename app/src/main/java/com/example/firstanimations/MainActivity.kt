package com.example.firstanimations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.AppDatabase
import com.example.firstanimations.data.models.MatchEntity
import com.example.firstanimations.data.models.StadiumEntity
import com.example.firstanimations.data.models.TeamEntity
import com.example.firstanimations.databinding.ActivityMainBinding
import com.example.firstanimations.presentation.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModelMatch by viewModels<MatchViewModel> {
        MatchViewModelFactory(
            AppDatabase.getMatchDatabase(
                applicationContext
            ).MatchDao()
        )
    }
    private val viewModelTeam by viewModels<TeamViewModel> {
        TeamViewModelFactory(
            AppDatabase.getTeamDatabase(
                applicationContext
            ).TeamDao()
        )
    }
    private val viewModelStadium by viewModels<StadiumViewModel> {
        StadiumViewModelFactory(
            AppDatabase.getStadiumDatabase(applicationContext).StadiumDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animations()
        clicks()
        val teams = listOf(
            TeamEntity(0, "Colombia", "Colombia"),
            TeamEntity(0, "Brazil", "Brazil"),
            TeamEntity(0, "Argentina", "Argentina"),
            TeamEntity(0, "Spain", "Spain"),
            TeamEntity(0, "Peru", "Peru"),
            TeamEntity(0, "Portugal", "Portugal"),
            TeamEntity(0, "Uruguay", "Uruguay"),
            TeamEntity(0, "Chile", "Chile"),
            TeamEntity(0, "Paraguay", "Paraguay"),
        )

        val stadiums = listOf(
            StadiumEntity(0, "Signal Iduna Park", "functioning"),
            StadiumEntity(0, "Camp Noe", "functioning"),
            StadiumEntity(0, "Wembley Stadium", "functioning"),
            StadiumEntity(0, "Old Trafford", "functioning"),
            StadiumEntity(0, "Estadio Maracanã", "functioning"),
            StadiumEntity(0, "Allianz Arena", "functioning"),
        )

        val matches = listOf(
            MatchEntity(1, 1, 2, 2, 0, 0, 0, "Final", 1),
            MatchEntity(2, 1, 2, 1, 0, 1, 0, "semifinal", 2),
            MatchEntity(3, 4, 2, 1, 3, 1, 0, "semifinal", 3),
            MatchEntity(4, 4, 5, 2, 0, 1, 0, "quarterfinals", 4),
        )
        lifecycleScope.launchWhenCreated { viewModelTeam.saveMatch(teams[0]).collect() }
        lifecycleScope.launchWhenCreated { viewModelTeam.saveMatch(teams[1]).collect() }
        lifecycleScope.launchWhenCreated { viewModelTeam.saveMatch(teams[2]).collect() }
        lifecycleScope.launchWhenCreated { viewModelTeam.saveMatch(teams[3]).collect() }
        lifecycleScope.launchWhenCreated { viewModelTeam.saveMatch(teams[4]).collect() }
        lifecycleScope.launchWhenCreated { viewModelTeam.saveMatch(teams[5]).collect() }
        lifecycleScope.launchWhenCreated { viewModelTeam.saveMatch(teams[6]).collect() }
        lifecycleScope.launchWhenCreated { viewModelTeam.saveMatch(teams[7]).collect() }
        lifecycleScope.launchWhenCreated { viewModelTeam.saveMatch(teams[8]).collect() }
        lifecycleScope.launchWhenCreated { viewModelStadium.saveMatch(stadiums[0]).collect() }
        lifecycleScope.launchWhenCreated { viewModelStadium.saveMatch(stadiums[1]).collect() }
        lifecycleScope.launchWhenCreated { viewModelStadium.saveMatch(stadiums[2]).collect() }
        lifecycleScope.launchWhenCreated { viewModelStadium.saveMatch(stadiums[3]).collect() }
        lifecycleScope.launchWhenCreated { viewModelStadium.saveMatch(stadiums[4]).collect() }
        lifecycleScope.launchWhenCreated { viewModelStadium.saveMatch(stadiums[5]).collect() }
        lifecycleScope.launchWhenCreated { viewModelMatch.saveMatch(matches[0]).collect() }
        lifecycleScope.launchWhenCreated { viewModelMatch.saveMatch(matches[1]).collect() }
        lifecycleScope.launchWhenCreated { viewModelMatch.saveMatch(matches[2]).collect() }
        lifecycleScope.launchWhenCreated { viewModelMatch.saveMatch(matches[3]).collect() }
        lifecycleScope.launchWhenCreated { viewModelMatch.fetchMatches().collect{
            when(it){
                is Result.Success->{
                    Log.e("onCreate: ",it.toString() )
                }
                is Result.Failure->{
                    Log.e("onCreate: ",it.exception.toString() )
                }
            }
        } }
    }

    private fun clicks() {
        binding.btnNextCourse.setOnClickListener {
            val i = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(i)
        }
    }

    private fun animations() {
        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        binding.headertitle.startAnimation(ttb)
        binding.subtitle.startAnimation(ttb)
        binding.icCards.startAnimation(AnimationUtils.loadAnimation(this, R.anim.stb))
        binding.resultOne.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btt))
        binding.resultTwo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btt2))
        binding.resultThree.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btt3))
        binding.btnNextCourse.startAnimation(AnimationUtils.loadAnimation(this, R.anim.btn_course))
    }
}