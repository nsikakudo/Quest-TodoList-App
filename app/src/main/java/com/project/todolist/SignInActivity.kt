package com.project.todolist

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.project.todolist.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var sf : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var binding: ActivitySignInBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_sign_in)
            setContentView(binding.root)

        supportActionBar?.hide()
        Log.i("MYTAG", "Wrong user Button")

        emailText = binding.edtEmail1
        passwordText = binding.edtPassword1
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()


        val user = intent.getStringExtra("USERNAME")
//        val email = intent.getStringExtra("EMAIL")
//        val password = intent.getStringExtra("PASSWORD")

        emailFocusListener()
        passwordFocusListener()


        binding.btnSignIn.setOnClickListener {
            val enterUserName = binding.edtEmail1.text.toString()
            val enterPassword = binding.edtPassword1.text.toString()

            if (enterUserName.isEmpty() || enterPassword.isEmpty()){
                Toast.makeText(this, "Please input your details!!!", Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                Toast.makeText(this, "Welcome $user", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }


    }

    private fun emailFocusListener(){
        binding.edtEmail1.setOnFocusChangeListener { _, focused ->
            if (!focused){
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.edtEmail1.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email Address"
        }
        return null
    }


    private fun passwordFocusListener(){
        binding.edtPassword1.setOnFocusChangeListener { _, focused ->
            if (!focused){
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.edtPassword1.text.toString()
        if (passwordText.length < 15){
            return "Minimum 8 character password"
        }
        if (!passwordText.matches(".*[A-Z]*.".toRegex())){
            return "Must Contain 1 Uppercase Character"
        }
        if (!passwordText.matches(".*[a-z]*.".toRegex())){
            return "Must Contain 1 Lowercase Character"
        }
        if (!passwordText.matches(".*[@#/$%^+-=]*.".toRegex())){
            return "Must Contain 1 Special Character (@#/\$%^+)"
        }
        return null
    }



    override fun onPause() {
        super.onPause()
        val name = emailText.text.toString()
        val password = passwordText.text.toString()
        editor.apply {
            putString("sf_name", name)
            putString("sf_password", password)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name", null)
        val password = sf.getString("sf_password", null)
        emailText.setText(name)
        passwordText.setText(password)
    }
}