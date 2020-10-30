package com.practice.signupmvc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.signupmvc.data.User
import com.practice.signupmvc.databinding.ActivityMainBinding
import com.practice.signupmvc.ext.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    private var db: AppDatabase? = null

    private var year = 0
    private var month = 0
    private var day = 0

    private lateinit var btnSignUp: Button
    private lateinit var edEmail: EditText
    private lateinit var edPw: EditText
    private lateinit var edPwCheck: EditText
    private lateinit var edNick: EditText
    private lateinit var edBirth: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var cbService: CheckBox
    private lateinit var cbMarketing: CheckBox

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        db = AppDatabase.getInstance(this)

        progressDialog = ProgressDialog(this)
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

         btnSignUp = binding.btnSignUp
         edEmail = binding.edEmail
         edPw = binding.edPw
         edPwCheck = binding.edPwCheck
         edNick = binding.edNick
         edBirth = binding.edBirth
         rgGender = binding.rgGender
         cbService = binding.cbService
         cbMarketing = binding.cbMarketing

        checkSignUp()

        progressDialog.setCancelable(false)

        btnSignUp.setOnClickListener {
            progressDialog.show()
            progressDismiss()
        }
        edBirth.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog(this, null, 1994, 9, 11).also {
                    it.show()
                    it.setOnDateSetListener { view, year, month, dayOfMonth ->
                        this.year = year
                        this.month = month
                        this.day = dayOfMonth
                        edBirth.setText(
                            "$year.${month + 1}.$dayOfMonth"
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun checkAge(): Boolean {
        SimpleDateFormat("yyyyMMdd").format(Date()).also {
            val (mYear, mMonth, mDay) = intArrayOf(
                it.substring(0, 4).toInt(),
                it.substring(4, 6).toInt(),
                it.substring(6).toInt()
            )
            var age = mYear - this.year
            if (mMonth < this.month) {
                age--
            } else if (mMonth == this.month) {
                if (mDay < this.day) age--
            }
            age++
            if (age < 14) {
                runOnUiThread {
                    Toast.makeText(
                        applicationContext,
                        "만 14세 미만은 회원가입이 불가합니다.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                return false
            }
        }
        return true
    }


    // true: 중복 아이디 존재
    // false: 중복 아이디 존재 x

    private fun checkDuplicatedId(): Boolean {

        db!!.userDao().isDuplicatedEmail(edEmail.text.toString()).also {

            if (it.isNotEmpty()) {
                runOnUiThread {
                    Toast.makeText(applicationContext, "중복된 아이디입니다.", Toast.LENGTH_LONG).show()
                }
                return true
            }
        }
        return false
    }

    private fun checkSuccess(): Boolean {
        return checkAge() && !checkDuplicatedId()
    }

    private fun progressDismiss() {

        thread {
            timerTask {
                progressDialog.dismiss()

                if (checkSuccess()) {
                    var gender =""

                    when (rgGender.checkedRadioButtonId) {
                        R.id.rb_man -> {
                            gender = "남자"
                            isAvailableGender = true
                        }
                        R.id.rb_woman -> {
                            gender = "여자"
                            isAvailableGender = true
                        }
                    }
                    db?.userDao()?.insert(
                        User(
                            null,
                            edEmail.text.toString(),
                            edPw.text.toString(),
                            edNick.text.toString(),
                            edBirth.text.toString(),
                            gender,
                            cbService.isChecked,
                            cbMarketing.isChecked
                        )
                    )

                    runOnUiThread {

                        startActivity(
                            Intent(
                                applicationContext,
                                InfoActivity::class.java
                            ).putExtra("email",edEmail.text.toString())
                        )
                    }
                }
            }.also { task ->
                Timer().let { timer ->
                    timer.schedule(task, 2000)
                }
            }
        }.join()
    }
    private fun checkSignUp(){
        edEmail.checkEmail(btnSignUp)
        edPw.checkPw(btnSignUp)
        edPwCheck.checkPwCheck(edPw, btnSignUp)
        edNick.checkNick(btnSignUp)
        edBirth.checkBirth(btnSignUp)
        rgGender.checkGender(btnSignUp)
        cbService.isServiceChecked(btnSignUp)
    }
}