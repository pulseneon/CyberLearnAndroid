package com.rudnev.imreading.utils

import android.content.Context

class TokenValidateUtil {
    companion object{
        private const val PREFS_NAME = "Token"
        private const val KEY_VALUE = "token"

        fun getToken(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(KEY_VALUE, null)
        }

        fun setToken(context: Context, token: String){
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(KEY_VALUE, token)
            editor.apply()
        }
    }
}