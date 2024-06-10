package com.globalxtreme.gxsalesdemo.model.interactor

import android.content.Context
import com.globalxtreme.gxsalesdemo.contract.LoginInteractorInterface
import com.globalxtreme.gxsalesdemo.contract.LoginPresenterInterface
import com.globalxtreme.gxsalesdemo.model.network.LoginNetwork
import com.globalxtreme.gxsalesdemo.model.prefs.PreferencesHelper
import org.json.JSONObject

class LoginInteractor(_presenter: LoginPresenterInterface, val context: Context):
    LoginInteractorInterface {

    private var presenter: LoginPresenterInterface = _presenter
    private var network: LoginNetwork? = null

    init {
        network = LoginNetwork(this, context)
    }

    override fun checkEmail(): String {
        val preferencesHelper = PreferencesHelper(context)
        return preferencesHelper.email ?: ""
    }

    override fun getAccessToken(email: String, password: String) {
        network?.getAccessToken(email, password)
    }

    override fun parseAccessToken(obj: JSONObject) {
        presenter.parseAccessToken(obj)
    }

    override fun savePreference(accessToken: String) {
        val preferencesHelper = PreferencesHelper(context)
        preferencesHelper.token = accessToken
    }

    override fun saveEmailPassword(email: String, password: String) {
        val preferencesHelper = PreferencesHelper(context)

        preferencesHelper.email = email
        preferencesHelper.password = password
    }

    override fun goToMain() {
        val preferencesHelper = PreferencesHelper(context)
        preferencesHelper.userId = "123"

        presenter.goToMain()
    }

    override fun showSnackBar(error: String) {
        presenter.showSnackBar(error)
    }

    override fun stopPostLoading() {
        presenter.stopPostLoading()
    }

}