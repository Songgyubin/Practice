package com.example.myapplication.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import com.example.myapplication.R
import java.util.regex.Pattern

var isAvailableEmail = false
var isAvailablePw = false
var isAvailablePwCheck = false
var isAvailableNick = false
var isAvailableBirth = false
var isAvailableGender = false
var isAvailableService = false

fun EditText.checkEmail(btn: Button) {

    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            isAvailableEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches()
            btn.isEnabled = checkAvailable()
        }
    })
}

fun EditText.checkPw(btn: Button) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // 비밀번호 유효성 검사식1 : 숫자, 특수문자가 포함되어야 한다.
            val symbol = "([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])"
            // 비밀번호 유효성 검사식2 : 영문자 대소문자가 적어도 하나씩은 포함되어야 한다.
            val alpha = "([a-z].*[A-Z])|([A-Z].*[a-z])"
            if (s!!.length < 8) isAvailablePw = false
            else if (!Pattern.compile(symbol).matcher(s).find()) isAvailablePw = false
            else if (!Pattern.compile(alpha).matcher(s).find()) isAvailablePw = false
            else isAvailablePw = true

            println("pw $isAvailablePw")
            btn.isEnabled = checkAvailable()
        }
    })
}

fun EditText.checkPwCheck(pw: EditText, btn: Button) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {
            isAvailablePwCheck = s.toString() == pw.text.toString()
            btn.isEnabled = checkAvailable()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            btn.isEnabled = checkAvailable()
        }
    })

}

fun EditText.checkNick(btn: Button) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            isAvailableNick = s!!.length in 8..30
            btn.isEnabled = checkAvailable()
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun EditText.checkBirth(btn: Button) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            isAvailableBirth = s!!.isNotEmpty()
            btn.isEnabled = checkAvailable()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })
}

fun RadioGroup.checkGender(btn: Button) {
    this.setOnCheckedChangeListener { group, checkedId ->
        isAvailableGender = checkedId != R.id.rb_no_choice
        btn.isEnabled = checkAvailable()
    }
}

fun CheckBox.isServiceChecked(btn: Button) {
    this.setOnCheckedChangeListener { buttonView, isChecked ->
        isAvailableService = isChecked
        btn.isEnabled = checkAvailable()
    }
}

private fun checkAvailable() =
    isAvailableEmail &&
            isAvailablePw &&
            isAvailablePwCheck &&
            isAvailableNick &&
            isAvailableBirth &&
            isAvailableGender &&
            isAvailableService
