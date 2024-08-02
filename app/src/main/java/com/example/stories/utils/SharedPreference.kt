package com.example.stories.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.stories.BaseAppClass
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SharedPreference {
    private var masterKey: MasterKey = MasterKey.Builder(BaseAppClass.appContext!!)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private var sharedPreference: SharedPreferences = EncryptedSharedPreferences.create(
        BaseAppClass.appContext!!,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    private val editor: SharedPreferences.Editor? = sharedPreference.edit()

    fun getString(key: String?): String {
        return sharedPreference.getString(key, "") ?: ""
    }

    fun putString(key: String?, value: String?) {
        editor?.putString(key, value)?.commit()
    }

    fun getInt(key: String?): Int {
        return sharedPreference.getInt(key, 0)
    }

    fun putInt(key: String?, value: Int) {
        editor?.putInt(key, value)?.commit()
    }

    fun getLong(key: String?): Long {
        return sharedPreference.getLong(key, 0L)
    }

    fun putLong(key: String?, value: Long) {
        editor?.putLong(key, value)?.commit()
    }

    fun getFloat(key: String?): Float {
        return sharedPreference.getFloat(key, 0.5f)
    }

    fun putFloat(key: String?, value: Float) {
        editor?.putFloat(key, value)?.commit()
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPreference.getBoolean(key, false)
    }

    fun putBoolean(key: String?, value: Boolean) {
        editor?.putBoolean(key, value)?.commit()
    }

    fun contains(key: String?): Boolean {
        return sharedPreference.contains(key)
    }

    fun remove(key: String?) {
        editor?.remove(key)?.commit()
    }

    fun getString(s: String?, name: String?): String {
        return sharedPreference.getString(s, name) ?: ""
    }

    companion object {
        const val MY_PREFERENCES = "MY_PREFERENCES"
        const val MODE = Context.MODE_PRIVATE
        private var pref: SharedPreference? = null

        @JvmStatic
        val instance: SharedPreference?
            get() {
                if (pref == null) {
                    pref = SharedPreference()
                }
                return pref
            }
    }


}