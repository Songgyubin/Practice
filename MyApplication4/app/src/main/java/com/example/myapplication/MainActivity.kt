package com.example.myapplication

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ext.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        val btnSignUp = binding.btnSignUp

        binding.edEmail.checkEmail(btnSignUp)
        binding.edPw.checkPw(btnSignUp)
        binding.edPwCheck.checkPwCheck(binding.edPw,btnSignUp)
        binding.edNick.checkNick(btnSignUp)
        binding.edBirth.checkBirth(btnSignUp)
        binding.rgGender.checkGender(btnSignUp)
        binding.cbService.isServiceChecked(btnSignUp)

        progressDialog.setCancelable(false)
        btnSignUp.setOnClickListener { progressDialog.show()
        Thread(Runnable {
            run {
                val task = object: TimerTask(){
                    override fun run() {
                        progressDialog.dismiss()
                    }
                }
                Timer().let { it.schedule(task,2000) }
            }
        }).start()
        }



    }
}