package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ext.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val btnSignUp = binding.btnSignUp
        binding.edEmail.checkEmail(btnSignUp)
        binding.edPw.checkPw(btnSignUp)
        binding.edPwCheck.checkPwCheck(binding.edPw,btnSignUp)
        binding.edNick.checkNick(btnSignUp)
        binding.edBirth.checkBirth(btnSignUp)
        binding.rgGender.checkGender(btnSignUp)
        binding.cbService.isServiceChecked(btnSignUp)

        btnSignUp.setOnClickListener { Toast.makeText(this,"회원가입",Toast.LENGTH_LONG).show() }

    }
}