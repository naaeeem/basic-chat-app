package com.example.basic_chat_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogIn: Button
    private lateinit var btnSinUp: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_pass)
        btnLogIn = findViewById(R.id.btn_login)
        btnSinUp = findViewById(R.id.btn_signup)

        btnSinUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()

            login(email, pass)
        }

    }

    private fun login(email: String, pass: String) {

        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for  jumping to home activity
                    val intent = Intent(this@LogIn, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // code for
                    Toast.makeText(this@LogIn, "Error", Toast.LENGTH_SHORT).show()
                }
            }
    }
}