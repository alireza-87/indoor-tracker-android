package com.midnightgeek.indoortracker

import android.content.Context
import android.content.SharedPreferences

class PrefHandler {
    constructor(context: Context){
        this.context=context
    }
    private lateinit var sharedPref: SharedPreferences
    private val PREF_NAME = "indoor"
    val TAG_USER_NAME = "user_name"
    val TAG_USER_PASS = "user_pass"

    val TAG_USER_RULE = "user_rule"
    val TAG_USER_SURENAME = "user_surename"
    val TAG_USER_TELL = "user_tell"
    val TAG_USER_EMAIL = "user_email"
    val TAG_USER_TOKEN = "user_token"

    val TAG_USER_ID = "node_id"
    lateinit var context: Context
    fun init() {
        sharedPref=this.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }



    fun setPreference(key: String?, value: Any?) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        if (value is Int) editor.putInt(
            key,
            (value as Int?)!!
        ) else if (value is String) editor.putString(
            key,
            value as String?
        ) else if (value is Boolean) editor.putBoolean(
            key,
            (value as Boolean?)!!
        ) else if (value is Long) editor.putLong(
            key,
            (value as Long?)!!
        ) else if (value is Set<*>) editor.putStringSet(
            key,
            value as Set<String?>?
        )
        editor.apply()
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return sharedPref.getInt(key, defaultValue)
    }

    fun getString(key: String?, defaultValue: String?): String? {
        return sharedPref.getString(key, defaultValue)
    }

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(key, defaultValue)
    }

    fun getLong(key: String?, defaultValue: Long): Long {
        return sharedPref.getLong(key, defaultValue)
    }

    fun getStringSet(
        key: String?,
        defaultValue: Set<String?>?
    ): Set<String?>? {
        return sharedPref.getStringSet(key, defaultValue)
    }

    fun clearTag(keyName: String?) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(keyName)
        editor.apply()
    }

    fun clear(): Boolean {
        return sharedPref.edit().clear().commit()
    }

    fun contain(key: String?): Boolean {
        return sharedPref.contains(key)
    }

    fun RemovingSinglePreference(key: String?) {
        sharedPref.edit().remove(key).apply()
    }

}