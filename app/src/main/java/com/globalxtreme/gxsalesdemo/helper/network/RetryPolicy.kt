package com.globalxtreme.gxsales.helper.network

import com.android.volley.DefaultRetryPolicy
import com.android.volley.toolbox.JsonObjectRequest
import com.globalxtreme.gxtechnician.helper.network.VolleyFileUploadRequest

class RetryPolicy {

    fun setRetryPolicyJsonObjectRequest(jsonObjectRequest: JsonObjectRequest) {
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            500000,
            0,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
    }

    fun setRetryPolicyUploadRequest(uploadRequest: VolleyFileUploadRequest) {
        uploadRequest.retryPolicy = DefaultRetryPolicy(
            500000,
            0,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
    }
}