package com.globalxtreme.gxsalesdemo.presenter

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import org.json.JSONObject
import android.Manifest
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.bumptech.glide.Glide
import com.globalxtreme.gxsales.helper.base.DefaultResponse
import com.globalxtreme.gxsalesdemo.R
import com.globalxtreme.gxsalesdemo.contract.LoginActivityInterface
import com.globalxtreme.gxsalesdemo.contract.LoginPresenterInterface
import com.globalxtreme.gxsalesdemo.model.interactor.LoginInteractor
import com.globalxtreme.gxsalesdemo.view.activity.LoginActivity
import com.globalxtreme.gxtechnician.helper.network.InternetCheck

class LoginPresenter(_view: LoginActivityInterface, val context: Context): LoginPresenterInterface {

    private var view: LoginActivityInterface = _view
    private var model: LoginInteractor? = null

    init {
        model = LoginInteractor(this, context)
    }

    override fun checkEmail() {
        view.binding.emailTxt.setText(model?.checkEmail())
    }

    override fun showHidePassword() {
        if (view.binding.passwordTxt.transformationMethod == PasswordTransformationMethod.getInstance()) {
            Glide.with(context).load(R.drawable.ic_hide).into(view.binding.showPasswordButton)
            view.binding.passwordTxt.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
        else {
            Glide.with(context).load(R.drawable.ic_show).into(view.binding.showPasswordButton)
            view.binding.passwordTxt.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    override fun validateForm() {
        when {
            view.binding.emailTxt.text.toString() == "" -> {
                view.showErrorFormValidation(view.binding.emailTxt,
                    DefaultResponse().EMAIL_CANT_EMPTY)
            }
            view.binding.passwordTxt.text.toString() == "" -> {
                view.showErrorFormValidation(view.binding.passwordTxt,
                    DefaultResponse().PASSWORD_CANT_EMPTY)
            }
            else -> {
                view.startPostLoading()
                checkInternet()
            }
        }
    }

    override fun checkInternet() {
        if (InternetCheck().isOnline(context)) {
            view.startPostLoading()
            getAccessToken()
        } else {
            showSnackBar(DefaultResponse().NO_INTERNET_CONNECTION)
        }
    }

    override fun getAccessToken() {
        val email = view.binding.emailTxt.text.toString()
        val password = view.binding.passwordTxt.text.toString()

        model?.getAccessToken(email, password)
    }

    override fun parseAccessToken(obj: JSONObject) {
        val accessToken = obj.getJSONObject("result").getString("token")

        model?.savePreference(accessToken)
    }

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = (context as LoginActivity).registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    override fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            }
//            else if ((context as LoginActivity).shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
//            }
            else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun goToMain() {
        view.goToMain()
    }

    override fun showSnackBar(error: String) {
        view.showSnackBar(error)
    }

    override fun stopPostLoading() {
        view.stopPostLoading()
    }

}