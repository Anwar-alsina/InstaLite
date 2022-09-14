package com.example.instalite

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.instalite.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            goPostsActivity()
        }

        binding.btnLogin.setOnClickListener{
            binding.btnLogin.isEnabled = false
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isBlank() || password.isBlank()){
                Toast.makeText(this,
                    "Email/Password cannot be Empty",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //Firebase Authentication
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task ->
                binding.btnLogin.isEnabled = true
                if (task.isSuccessful){
                    Toast.makeText(this,
                        "Login Successful",
                        Toast.LENGTH_SHORT).show()
                    goPostsActivity()
                }else{
                    Log.i(TAG,"SignInWithEmail Failed",task.exception)
                    Toast.makeText(this,
                        "Authentication Failed",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goPostsActivity() {

        val intent = Intent(this,PostsActivity::class.java)
        startActivity(intent)
        finish()
    }
}