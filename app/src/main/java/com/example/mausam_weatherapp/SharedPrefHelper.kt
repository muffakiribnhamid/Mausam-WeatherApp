package com.example.mausam_weatherapp

import android.content.Context
import android.preference.PreferenceManager

object SharedPrefHelper {
    fun save_location(context : Context, Location:String) {
        PreferenceManager.getDefaultSharedPreferences(
            context
        ).edit()
            .putString("Location",Location)
            .apply()

    }
    fun getLocation(context: Context) : String? {
        return PreferenceManager.getDefaultSharedPreferences(
            context
        ).getString("Location","")



    }
}