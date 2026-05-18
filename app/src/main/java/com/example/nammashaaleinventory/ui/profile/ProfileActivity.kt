package com.example.nammashaaleinventory.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.R
import com.example.nammashaaleinventory.ui.auth.LoginActivity
import com.example.nammashaaleinventory.ui.dashboard.DashboardActivity
import com.example.nammashaaleinventory.ui.assets.AssetListActivity
import com.example.nammashaaleinventory.ui.assets.ScanAssetActivity
import com.example.nammashaaleinventory.ui.reports.ReportsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var ivProfile: ImageView

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            if (imageUri != null) {
                ivProfile.setImageURI(imageUri)
                ivProfile.clearColorFilter()
                Toast.makeText(this, "Profile image updated locally", Toast.LENGTH_SHORT).show()
                // In a real app, you would upload this to Firebase Storage here
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ivProfile = findViewById(R.id.ivProfile)
        
        setupProfileInfo()
        setupMenuActions()
        setupNavigation()
        
        findViewById<FloatingActionButton>(R.id.btnEditProfileImage).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(intent)
        }
    }

    private fun setupProfileInfo() {
        val user = FirebaseAuth.getInstance().currentUser
        findViewById<TextView>(R.id.tvName).text = "Admin User"
        findViewById<TextView>(R.id.tvEmail).text = user?.email ?: "admin@school.com"
        findViewById<TextView>(R.id.tvRole).text = "Administrator"
    }

    private fun setupMenuActions() {
        findViewById<android.view.View>(R.id.menuMyProfile).setOnClickListener {
            // Navigate to personal profile edit
        }
        
        findViewById<android.view.View>(R.id.btnLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        
        // Setup labels for included menu items
        setupMenuItem(R.id.menuMyProfile, "My Profile", android.R.drawable.ic_menu_myplaces)
        setupMenuItem(R.id.menuSchoolDetails, "School Details", android.R.drawable.ic_menu_agenda)
        setupMenuItem(R.id.menuUsers, "Users & Permissions", android.R.drawable.ic_menu_myplaces)
        setupMenuItem(R.id.menuBackup, "Backup & Restore", android.R.drawable.ic_menu_save)
        setupMenuItem(R.id.menuSettings, "Settings", android.R.drawable.ic_menu_manage)
    }
    
    private fun setupMenuItem(id: Int, title: String, iconRes: Int) {
        val view = findViewById<android.view.View>(id)
        view.findViewById<TextView>(R.id.tvTitle).text = title
        view.findViewById<android.widget.ImageView>(R.id.ivIcon).setImageResource(iconRes)
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_profile
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_assets -> {
                    startActivity(Intent(this, AssetListActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_scan -> {
                    startActivity(Intent(this, ScanAssetActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_reports -> {
                    startActivity(Intent(this, ReportsActivity::class.java))
                    finish()
                    false
                }
                R.id.nav_profile -> true
                else -> false
            }
        }
    }
}