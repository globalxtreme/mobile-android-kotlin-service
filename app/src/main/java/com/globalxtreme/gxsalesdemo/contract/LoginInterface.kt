package com.globalxtreme.gxsalesdemo.contract

import android.widget.EditText
import com.globalxtreme.gxsalesdemo.databinding.ActivityLoginBinding
import org.json.JSONObject

interface LoginActivityInterface {
    var binding: ActivityLoginBinding

    fun initVersion()
    fun initListener()
    fun showErrorFormValidation(editText: EditText, error: String)
    fun goToMain()
    fun showSnackBar(error: String)
    fun startPostLoading()
    fun stopPostLoading()
}

interface LoginPresenterInterface {
    fun checkEmail()
    fun showHidePassword()
    fun validateForm()
    fun checkInternet()
    fun getAccessToken()
    fun parseAccessToken(obj: JSONObject)
    fun askNotificationPermission()
    fun goToMain()
    fun showSnackBar(error: String)
    fun stopPostLoading()
}

interface LoginInteractorInterface {
    fun checkEmail(): String
    fun getAccessToken(email: String, password: String)
    fun parseAccessToken(obj: JSONObject)
    fun savePreference(accessToken: String)
    fun saveEmailPassword(email: String, password: String)
    fun goToMain()
    fun showSnackBar(error: String)
    fun stopPostLoading()
}

interface LoginNetworkInterface {
    fun getAccessToken(email: String, password: String)
}

interface LoginRouterInterface {
    fun goToMain()
}