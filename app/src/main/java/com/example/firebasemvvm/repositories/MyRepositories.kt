package com.example.firebasemvvm.repositories

import androidx.lifecycle.MutableLiveData
import com.example.firebasemvvm.data.UserData
import com.example.firebasemvvm.util.BaseResponse
import com.google.firebase.firestore.FirebaseFirestore

object MyRepositories {

    fun getUserData(id: Int): MutableLiveData<BaseResponse<UserData>> {
        val mutableLiveData = MutableLiveData<BaseResponse<UserData>>()
        FirebaseFirestore.getInstance()
            .collection("USERS")
            .document("$id")
            .get()
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    mutableLiveData.postValue(
                        BaseResponse.error(
                            it.exception!!.message ?: "",
                            null
                        )
                    )
                    return@addOnCompleteListener
                }
                if (it.result == null) {
                    mutableLiveData.postValue(
                        BaseResponse.failed(
                            "data is null",
                            null
                        )
                    )
                    return@addOnCompleteListener
                }
                val data = it.result!!.toObject(UserData::class.java)
                mutableLiveData.postValue(BaseResponse.success(data))
            }

        return mutableLiveData
    }
}