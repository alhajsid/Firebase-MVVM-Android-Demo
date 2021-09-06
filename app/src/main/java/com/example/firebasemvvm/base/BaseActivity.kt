package com.example.firebasemvvm.base

import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasemvvm.mvvm.MyViewModel
import com.example.firebasemvvm.util.SharedPref

abstract class BaseActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel
    lateinit var sharedPref: SharedPref

    lateinit var progressDialogLayout: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressDialogLayout = ProgressDialog(this)
        sharedPref = SharedPref(this)
    }
}