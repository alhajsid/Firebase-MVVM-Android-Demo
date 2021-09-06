package com.example.firebasemvvm

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AlertDialogLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebasemvvm.base.BaseActivity
import com.example.firebasemvvm.mvvm.MyViewModel
import com.example.firebasemvvm.util.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialogLayout = ProgressDialog(this)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        viewModel.myData.observe(this,{
            when(it.status){
                Status.SUCCESS -> {
                    progressDialogLayout.hide()
                    tv.text = it.data.toString()

                }
                Status.ERROR -> {
                    progressDialogLayout.hide()
                    tv.text = it.message.toString()

                }
                Status.LOADING ->{
                    progressDialogLayout.show()
                }
                Status.FAILED -> {
                    progressDialogLayout.hide()
                    tv.text = it.message.toString()

                }
            }
        })
        viewModel.getUserData(5)
    }
}