package com.example.firebasemvvm.util

import android.content.Context
import android.content.SharedPreferences
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type

class SharedPref(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SuperMD", Context.MODE_PRIVATE)

    var email: String?
        get() = sharedPreferences.getString("email", null)
        set(value) {
            sharedPreferences.edit().putString("email", value).apply()
        }

    var notification_time_id: Int
        get() = sharedPreferences.getInt("notification_time_id", -1)
        set(value) {
            sharedPreferences.edit().putInt("notification_time_id", value).apply()
        }

    var hasSubscribeOneSignal: Boolean
        get() = sharedPreferences.getBoolean("hasSubscribeOneSignal", false)
        set(value) {
            sharedPreferences.edit().putBoolean("hasSubscribeOneSignal", value).apply()
        }

    var listOfAppointmentId: ArrayList<Int>
        get() {
            val listType: Type = object : TypeToken<List<Int>?>() {}.type
            return Gson().fromJson(
                sharedPreferences.getString("listOfAppointmentId", ""),
                listType
            ) ?: arrayListOf<Int>()
        }
        set(bVal) {
            val gson = Gson()
            val data = gson.toJson(bVal)
            val editor = sharedPreferences.edit()
            editor.putString("listOfAppointmentId", data)
            editor.apply()
        }

    fun clear (){
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
