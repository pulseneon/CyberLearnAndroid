package com.rudnev.imreading.utils

import android.content.Context

class FirstShowActivityUtil {
    companion object{
        private const val PREFS_NAME = "FirstShowActivity"
        private const val KEY_VALUE = "isShow"

        fun wasFirstActivityShown(context: Context): Boolean{
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(KEY_VALUE, false)
        }

        fun markFirstActivityShown(context: Context){
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean(KEY_VALUE, true)
            editor.apply()
        }
    }
}