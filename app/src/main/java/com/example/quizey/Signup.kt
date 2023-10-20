package com.example.quizey
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class Signup : AppCompatActivity() {

    private lateinit var nameLayout: TextInputLayout
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var confirmPasswordLayout: TextInputLayout
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var signupBtn: Button
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        nameLayout = findViewById(R.id.fullNameLayout)
        emailLayout = findViewById(R.id.emailLayout)
        passwordLayout = findViewById(R.id.passwordLayout)
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout)
        nameEditText = findViewById(R.id.fullNameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        signupBtn = findViewById(R.id.signup_btn)
        loginBtn=findViewById(R.id.login_btn)

        loginBtn.setOnClickListener{
            val intent1=Intent(this,Login::class.java)
            startActivity(intent1)
            finish()
        }

        // Set click listener on Signup button
        signupBtn.setOnClickListener {
            // Check if all fields are valid
            if (validateFields()) {

                Toast.makeText(
                    this,
                    "Data saved successfully",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    // Function to validate all fields
    private fun validateFields(): Boolean {
        var isValid = true

        if (nameEditText.text.isNullOrEmpty()) {
            nameLayout.error = "Name cannot be empty"
            isValid = false
        } else {
            nameLayout.error = null
        }

        if (emailEditText.text.isNullOrEmpty()) {
            emailLayout.error = "Email cannot be empty"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString())
                .matches()
        ) {
            emailLayout.error = "Invalid email address"
            isValid = false
        } else {
            emailLayout.error = null
        }

        if (passwordEditText.text.isNullOrEmpty()) {
            passwordLayout.error = "Password cannot be empty"
            isValid = false
        } else {
            passwordLayout.error = null
        }

        if (confirmPasswordEditText.text.isNullOrEmpty()) {
            confirmPasswordLayout.error = "Confirm Password cannot be empty"
            isValid = false
        } else if (confirmPasswordEditText.text.toString() != passwordEditText.text.toString()) {
            confirmPasswordLayout.error = "Passwords do not match"
            isValid = false
        } else {
            confirmPasswordLayout.error = null
        }

        return isValid
    }


}
