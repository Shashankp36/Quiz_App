package com.example.quizey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val signup=findViewById<Button>(R.id.signup_btn)
        val login=findViewById<Button>(R.id.login_btn)
        val uname=findViewById<EditText>(R.id.email)
        val pass=findViewById<EditText>(R.id.pass)
        signup.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
            finish()
        }
        login.setOnClickListener{
            if (uname.text.toString() == "admin" && pass.text.toString() == "123") {
                Toast.makeText(this, "Login Successfuly", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}