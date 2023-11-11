package com.example.login

import android.R.attr.data
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class Register : AppCompatActivity() {

    lateinit var btn: Button
    lateinit var email: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var auth :FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn = findViewById(R.id.btn_reg)
        email = findViewById(R.id.email)
        password = findViewById(R.id.pws)


        auth = FirebaseAuth.getInstance()

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

            auth.createUserWithEmailAndPassword(emailTosend, pswToSend)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        Toast.makeText(
                            baseContext,
                            "Compte is created.",
                            Toast.LENGTH_SHORT,
                        ).show()



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