package com.project.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.project.todolist.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_sign_up)
        setContentView(binding.root)

        supportActionBar?.hide()

//        val editName = findViewById<EditText>(R.id.edtName)
//        val editEmail = findViewById<EditText>(R.id.edtEmail)
//        val editPassword = findViewById<EditText>(R.id.edtPassword)
//        val btnSignUp = findViewById<Button>(R.id.btn_signup)

        emailFocusListener()
        userNameFocusListener()
        passwordFocusListener()


        binding.btnSignup.setOnClickListener {
            val enteredName = binding.edtName.text.toString()
            val enteredEmail = binding.edtEmail.text.toString()
            val enteredPassword = binding.edtPassword.text.toString()

            if (enteredName.isEmpty() || enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Please input your details!!!", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("USERNAME", enteredName)
                intent.putExtra("EMAIL", enteredEmail)
                intent.putExtra("PASSWORD", enteredPassword)
                startActivity(intent)
                finish()
            }
        }
    }


    private fun emailFocusListener(){
        binding.edtEmail.setOnFocusChangeListener { _, focused ->
            if (!focused){
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.edtEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email Address"
        }
        return null
    }

    private fun userNameFocusListener(){
        binding.edtName.setOnFocusChangeListener { _, focused ->
            if (!focused){
                binding.nameContainer.helperText = validName()
            }
        }
    }

    private fun validName(): String? {
        val nameText = binding.edtName.text.toString()
        if (nameText.length > 10){
            return "username too long"
        }
        if (nameText.matches(".*[A-Z]*.".toRegex())){
            return "Must Contain 1 Uppercase Character"
        }
        if (nameText.matches(".*[a-z]*.".toRegex())){
            return "Must Contain Lowercase Characters"
        }
        return null
    }

    private fun passwordFocusListener(){
        binding.edtPassword.setOnFocusChangeListener { _, focused ->
            if (!focused){
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.edtPassword.text.toString()
        if (passwordText.length < 10){
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
}


