package com.globalxtreme.gxsalesdemo.model.network

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.globalxtreme.gxsales.helper.base.DefaultResponse
import com.globalxtreme.gxsales.helper.config.Configuration
import com.globalxtreme.gxsales.helper.network.RetryPolicy
import com.globalxtreme.gxsalesdemo.contract.LoginInteractorInterface
import com.globalxtreme.gxsalesdemo.contract.LoginNetworkInterface
import com.globalxtreme.gxtechnician.helper.network.NetworkResponseMessage
import com.globalxtreme.gxtechnician.helper.network.VolleyRequest
import org.json.JSONException
import org.json.JSONObject

class LoginNetwork(_model: LoginInteractorInterface, val context: Context): LoginNetworkInterface {

    private var model: LoginInteractorInterface = _model

    override fun getAccessToken(email: String, password: String) {
        val url = Configuration().BASE_URL + Configuration().AUTH_URL + Configuration().LOGIN_URL
        val queue = Volley.newRequestQueue(context)

        var body = JSONObject()
        body.put("email", email)
        body.put("password", password)
        body = VolleyRequest(context).bodySecure(body)

        val jsonObjectRequest = object : JsonObjectRequest(
            url, body,
            Response.Listener<JSONObject> { response ->
                model.stopPostLoading()
                try {
                    Log.d("RESULT", response.toString())
                    model.saveEmailPassword(email, password)
                    model.parseAccessToken(response)
                    model.goToMain()

                } catch (e: JSONException) {
                    e.printStackTrace()
                    model.showSnackBar(e.message ?: DefaultResponse().SOMETHING_WENT_WRONG)
                }
            },
            Response.ErrorListener { volleyError ->
                model.stopPostLoading()
                if (volleyError.networkResponse.statusCode == 401) {
                    model.showSnackBar(DefaultResponse().ERROR_401)
                } else {
                    model.showSnackBar(NetworkResponseMessage(context).getMessage(volleyError))
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>? {
                return VolleyRequest(context).headerSecureWithoutToken(body)
            }
        }

        RetryPolicy().setRetryPolicyJsonObjectRequest(jsonObjectRequest)
        queue.add(jsonObjectRequest)
    }

}