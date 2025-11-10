package com.kevin.proyectomovileskch.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.ui.main.MainActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        btnSignIn.setOnClickListener {
            // Simple mock authentication: accept any non-empty username
            val user = etUsername.text.toString().trim()
            if (user.isNotEmpty()) {
                // Go to MainActivity
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            } else {
                Toast.makeText(this, getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister.setOnClickListener {
            // For prototype, show toast or navigate to RegisterActivity if implemented
            Toast.makeText(this, "Register flow not implemented in prototype", Toast.LENGTH_SHORT).show()
        }
    }
}
