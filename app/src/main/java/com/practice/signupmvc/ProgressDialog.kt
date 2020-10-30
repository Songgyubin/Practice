package com.practice.signupmvc
import android.app.Dialog
import android.content.Context
import android.view.Window



class ProgressDialog(context: Context?) : Dialog(context!!) {
    init {
        println("progress thread1")
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_progress)
    }

    override fun show() {
        super.show()
        println("progress thread2")
    }

    override fun dismiss() {
        super.dismiss()
        println("progress thread3")
    }

}