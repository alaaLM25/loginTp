package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {


    lateinit var btn: Button
    lateinit var email: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var register: TextView
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn = findViewById(R.id.btn_log)
        email = findViewById(R.id.email)
        password = findViewById(R.id.pws)
        register = findViewById(R.id.to_reg)
        auth = FirebaseAuth.getInstance()




        register.setOnClickListener {

            val intent = Intent(this, Register::class.java)
            startActivity(intent)

        }




        btn.setOnClickListener {
            if (email.text.toString().isEmpty()) {
                Toast.makeText(
                    this, "email is empty",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            if (password.text.toString().isEmpty()) {
                Toast.makeText(
                    this, "password is empty",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            val emailTosend = email.text.toString()
            val pswToSend = password.text.toString()

            auth.signInWithEmailAndPassword(emailTosend, pswToSend)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
        }
    }


}