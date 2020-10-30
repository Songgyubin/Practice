package com.practice.signupmvc

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.practice.signupmvc.databinding.ActivityMainBinding
import com.practice.signupmvc.ext.*
import java.util.*
import kotlin.concurrent.thread
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnSignUp = binding.btnSignUp

        binding.edEmail.checkEmail(btnSignUp)
        binding.edPw.checkPw(btnSignUp)
        binding.edPwCheck.checkPwCheck(binding.edPw, btnSignUp)
        binding.edNick.checkNick(btnSignUp)
        binding.edBirth.checkBirth(btnSignUp)
        binding.rgGender.checkGender(btnSignUp)
        binding.cbService.isServiceChecked(btnSignUp)

        progressDialog.setCancelable(false)
        btnSignUp.setOnClickListener {
            progressDialog.show()
            thread { timerTask { progressDialog.dismiss() }.also { task ->
                Timer().let { timer ->
                    timer.schedule(
                            task,
                            2000
                    )
                }
            }
            }
        }
    }
}