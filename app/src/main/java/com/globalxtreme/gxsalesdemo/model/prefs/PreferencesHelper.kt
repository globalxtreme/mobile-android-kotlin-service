package com.globalxtreme.gxsalesdemo.model.prefs

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context) {

    companion object {
        const val DEVELOP_MODE = false
        private const val USER_ID = "data.source.prefs.USER_ID"
        private const val EMAIL = "data.source.prefs.EMAIL"
        private const val PASSWORD = "data.source.prefs.PASSWORD"
        private const val TOKEN = "data.source.prefs.TOKEN"
        private const val FORCE_RELOGIN = "data.source.prefs.FORCE_RELOGIN"
        private const val COMPANY_OFFICE_ID = "data.source.prefs.COMPANY_OFFICE_ID"

        private const val NAME = "data.source.prefs.NAME"
        private const val DIVISION = "data.source.prefs.DIVISION"
        private const val USER_IMAGE = "data.source.prefs.USER_IMAGE"
        private const val COMPANY_OFFICE = "data.source.prefs.COMPANY_OFFICE"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    // save device token
    var userId = preferences.getString(USER_ID, "")
        set(value) = preferences.edit().putString(USER_ID, value).apply()

    var email = preferences.getString(EMAIL, "")
        set(value) = preferences.edit().putString(EMAIL, value).apply()

    var password = preferences.getString(PASSWORD, "")
        set(value) = preferences.edit().putString(PASSWORD, value).apply()

    var token = preferences.getString(TOKEN, "")
        set(value) = preferences.edit().putString(TOKEN, value).apply()

    var forceRelogin = preferences.getInt(FORCE_RELOGIN, 0)
        set(value) = preferences.edit().putInt(FORCE_RELOGIN, value).apply()

    var companyOfficeId = preferences.getInt(COMPANY_OFFICE_ID, 0)
        set(value) = preferences.edit().putInt(COMPANY_OFFICE_ID, value).apply()

    var name = preferences.getString(NAME, "")
        set(value) = preferences.edit().putString(NAME, value).apply()

    var division = preferences.getString(DIVISION, "")
        set(value) = preferences.edit().putString(DIVISION, value).apply()

    var userImage = preferences.getString(USER_IMAGE, "")
        set(value) = preferences.edit().putString(USER_IMAGE, value).apply()

    var companyOffice = preferences.getString(COMPANY_OFFICE, "")
        set(value) = preferences.edit().putString(COMPANY_OFFICE, value).apply()
}