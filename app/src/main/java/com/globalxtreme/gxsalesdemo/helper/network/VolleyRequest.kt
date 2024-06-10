package com.globalxtreme.gxtechnician.helper.network

import android.content.Context
import android.util.Log
import com.globalxtreme.gxsalesdemo.model.prefs.PreferencesHelper
import org.json.JSONObject
import java.util.*

class VolleyRequest(context: Context) {

    private val preferencesHelper = PreferencesHelper(context)

    fun headerSecure(body: JSONObject): MutableMap<String, String>? {
        Log.d("BODY JSON", body.toString())

        val params = HashMap<String, String>()
        params["Authorization"] = "Bearer ${preferencesHelper.token}"

        Log.d("HEADER JSON", params.toString())

        return params
    }

    fun headerSecureWithoutToken(body: JSONObject): MutableMap<String, String>? {
        Log.d("SIGNATURE BODY", body.toString())

        return HashMap()
    }

    fun bodySecure(params: JSONObject): JSONObject {
        Log.d("BODY", params.toString())

        return params
    }

}