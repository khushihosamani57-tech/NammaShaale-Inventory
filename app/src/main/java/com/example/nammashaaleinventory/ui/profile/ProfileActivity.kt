package com.example.nammashaaleinventory.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.nammashaaleinventory.databinding.ActivityProfileBinding
import com.example.nammashaaleinventory.ui.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var selectedImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            binding.ivProfile.setImageURI(it)
            // In a real app, you would upload this to Firebase Storage
            Toast.makeText(this, "Profile image updated locally", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupProfileData()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            saveProfileChanges()
        }

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.cardProfileImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
    }

    private fun setupProfileData() {
        val userId = auth.currentUser?.uid
        val email = auth.currentUser?.email

        binding.etEmail.setText(email)

        if (userId != null) {
            binding.progressBar.visibility = View.VISIBLE
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    binding.progressBar.visibility = View.GONE
                    if (document != null && document.exists()) {
                        val name = document.getString("name")
                        val phone = document.getString("phone")
                        
                        binding.etName.setText(name)
                        binding.etPhone.setText(phone)
                    }
                }
                .addOnFailureListener {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Failed to load profile", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun saveProfileChanges() {
        val userId = auth.currentUser?.uid ?: return
        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()

        if (name.isEmpty()) {
            binding.etName.error = "Name cannot be empty"
            return
        }

        val profileUpdates = mutableMapOf<String, Any>(
            "name" to name,
            "phone" to phone
        )

        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.isEnabled = false

        db.collection("users").document(userId).update(profileUpdates)
            .addOnSuccessListener {
                binding.progressBar.visibility = View.GONE
                binding.btnSave.isEnabled = true
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                binding.progressBar.visibility = View.GONE
                binding.btnSave.isEnabled = true
                Toast.makeText(this, "Error updating profile: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
