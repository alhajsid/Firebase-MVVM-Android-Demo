package com.example.firebasemvvm.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasemvvm.data.UserData
import com.example.firebasemvvm.repositories.MyRepositories
import com.example.firebasemvvm.util.BaseResponse

class MyViewModel: ViewModel() {

    var repo = MyRepositories

    var myData = MutableLiveData<BaseResponse<UserData>>()

    fun getUserData(id:Int){
        myData.postValue(BaseResponse.loading(null))
        repo.getUserData(id).observeForever {
            myData.postValue(it)
        }
    }
}