package com.practice.signupmvc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.practice.signupmvc.databinding.ActivityInfoBinding
import kotlin.concurrent.thread

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info)
        val tvInfo = binding.tvInfo
        db = AppDatabase.getInstance(this)
        thread {
            db?.userDao()?.getUserByEmail(intent.getStringExtra("email")!!).let {
                runOnUiThread {
                    tvInfo.setText("이메일: ${it?.email} \n 닉네임: ${it?.nick} \n 생년월일: ${it?.birth} \n 성별: ${it?.gender} \n 약관 동의: ${it?.isAgreeService} \n 마케팅 동의: ${it?.isAgreemarketing} \n")
                }
            }
        }
    }
}