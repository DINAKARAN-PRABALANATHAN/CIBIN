package com.cibinenterprizes.cibin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.login_window.*
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.login_window)
        auth = FirebaseAuth.getInstance()

        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))

        }

        btnLogin.setOnClickListener {
            if(editTextEmail.text.trim().toString().isNotEmpty() || editTextPassword.text.trim().toString().isNotEmpty()){

                signInUser(editTextEmail.text.trim().toString(), editTextPassword.text.trim().toString())

            }else{
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun signInUser(email:String, password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    startActivity(Intent(this, MainActivity1::class.java))
                    finish()
                }else{
                    Toast.makeText(this, "Error !! "+task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }
}