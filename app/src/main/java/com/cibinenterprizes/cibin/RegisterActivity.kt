package com.cibinenterprizes.cibin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        btnRegister.setOnClickListener {
            if(editEmail.text.trim().toString().isNotEmpty() || editPassword.text.trim().toString().isNotEmpty()){
                createUser(editEmail.text.trim().toString(), editPassword.text.trim().toString())
                startActivity(Intent(this, MainActivity1::class.java))
                Toast.makeText(this, "Registration Successful",Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this, "Input Reqired",Toast.LENGTH_LONG).show()

            }
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))

        }
    }
    private fun createUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
            if(task.isSuccessful){
                Toast.makeText(this, "User Creation is Successful...",Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this, "User Creation is Failed...",Toast.LENGTH_LONG).show()

            }

        }

    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser

        if(user != null){
            startActivity(Intent(this, MainActivity1::class.java))

        }
    }
}