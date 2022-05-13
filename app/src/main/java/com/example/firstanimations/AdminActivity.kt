package com.example.firstanimations

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.firstanimations.core.Constants
import com.example.firstanimations.databinding.ActivityAdminBinding
import com.example.firstanimations.databinding.NavHeaderAdminBinding
import org.w3c.dom.Text

class AdminActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarAdmin.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_admin)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_matches
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.appBarAdmin.imgAddUser.setOnClickListener {
            val i = Intent(this@AdminActivity, RegisterActivity::class.java)
            startActivity(i)
        }
        val navHeader = binding.navView.getHeaderView(0)
        val nameMenu = navHeader.findViewById<TextView>(R.id.txtName)
        val emailMenu = navHeader.findViewById<TextView>(R.id.txtEmail)
        val exitMenu = navHeader.findViewById<TextView>(R.id.txtSignOf)
        nameMenu.text = Constants.CURRENT_USER!!.name
        emailMenu.text = Constants.CURRENT_USER!!.email

        emailMenu.setOnClickListener {
            Constants.USER_UPDATE = Constants.CURRENT_USER!!
            val i = Intent(this@AdminActivity,UpdateUserActivity::class.java)
            startActivity(i)
        }
        nameMenu.setOnClickListener {
            Constants.USER_UPDATE = Constants.CURRENT_USER!!
            val i = Intent(this@AdminActivity,UpdateUserActivity::class.java)
            startActivity(i)
        }
        exitMenu.setOnClickListener {
            val i = Intent(this@AdminActivity,AuthActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.admin, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_admin)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}