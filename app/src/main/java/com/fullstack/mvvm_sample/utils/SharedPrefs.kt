package com.fullstack.mvvm_sample.utils

import android.content.Context
import android.content.SharedPreferences

enum class PrefKeys(val key: String) {
    NAME_KEY("Fullstack software"),
}

class Preferences(private val context: Context) {

    fun getPrefs(key: String): String? {
        val sharedPrefs = getPreferences()
        return sharedPrefs.getString(key, null)
    }

    fun setPrefs(key: String, value: String? = null) {
        val sharedPrefs = getPreferences()
        sharedPrefs.edit().let {
            it.putString(key, value)
            it.apply()
        }
    }
    fun getBooleanPrefs(key: String): Boolean {
        val sharedPrefs = getPreferences()
        return sharedPrefs.getBoolean(key, false)
    }

    fun setBooleanPrefs(key: String, value: Boolean = false) {
        val sharedPrefs = getPreferences()
        sharedPrefs.edit().let {
            it.putBoolean(key, value)
            it.apply()
        }
    }

    private fun getPreferences(): SharedPreferences {
        return context.getSharedPreferences(PrefKeys.NAME_KEY.key, 0)
    }
}