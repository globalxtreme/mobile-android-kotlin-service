package com.globalxtreme.gxtechnician.helper.network

import android.content.Context
import android.util.Log
import com.android.volley.VolleyError
import com.globalxtreme.gxsales.helper.base.DefaultResponse
import com.globalxtreme.gxsales.helper.config.ResetAppDataHelper
import org.json.JSONObject

class NetworkResponseMessage(val context: Context) {

    fun getMessage(volleyError: VolleyError): String {
        if (volleyError.networkResponse != null) {
            return if (volleyError.networkResponse.data != null) {
                try {
                    val errorObj = JSONObject(String(volleyError.networkResponse.data, Charsets.UTF_8))
                    Log.e("error", errorObj.toString())
                    if (errorObj.has("status")) {
                        if (volleyError.networkResponse.statusCode == 401) {
                            ResetAppDataHelper(context).clearPreferencesHelper()
                            ResetAppDataHelper(context).clearDatabase()
                            ResetAppDataHelper(context).logout()
                            DefaultResponse().ERROR_401
                        }
                        else {
                            errorObj.getJSONObject("status").getString("message") + ": " +
                                errorObj.getJSONObject("status").getString("internalMsg") +
                                    " (attributes: ${errorObj.getJSONObject("status").getString("attributes")})"
                        }
                    } else {
                        handleStatusCodeDefault(volleyError.networkResponse.statusCode)
                    }
                } catch (t: Throwable) {
                    handleStatusCodeDefault(volleyError.networkResponse.statusCode)
                }
            } else {
                handleStatusCodeDefault(volleyError.networkResponse.statusCode)
            }
        }
        else {
            return volleyError.message.toString()
        }
    }

    private fun handleStatusCodeDefault(errorCode: Int): String {
        return when (errorCode) {
            400 -> {
                DefaultResponse().ERROR_400
            }
            401 -> {
                ResetAppDataHelper(context).clearPreferencesHelper()
                ResetAppDataHelper(context).clearDatabase()
                ResetAppDataHelper(context).logout()
                DefaultResponse().ERROR_401
            }
            404 -> {
                DefaultResponse().ERROR_404
            }
            408 -> {
                DefaultResponse().ERROR_408
            }
            413 -> {
                DefaultResponse().ERROR_413
            }
            422 -> {
                DefaultResponse().ERROR_422
            }
            429 -> {
                DefaultResponse().ERROR_429
            }
            500 -> {
                DefaultResponse().ERROR_500
            }
            503 -> {
                DefaultResponse().ERROR_503
            }
            else -> {
                DefaultResponse().ERROR_OCCURRED
            }
        }
    }

}