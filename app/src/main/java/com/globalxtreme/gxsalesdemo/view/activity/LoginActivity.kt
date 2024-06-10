package com.globalxtreme.gxsalesdemo.view.activity

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.globalxtreme.gxsalesdemo.contract.LoginActivityInterface
import com.globalxtreme.gxsalesdemo.databinding.ActivityLoginBinding
import com.globalxtreme.gxsalesdemo.presenter.LoginPresenter
import com.globalxtreme.gxsalesdemo.router.LoginRouter
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), LoginActivityInterface {
    private var presenter: LoginPresenter? = null
    private var router: LoginRouter? = null

    override lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVersion()
        initListener()

        presenter = LoginPresenter(this, this)
        router = LoginRouter(this, this)

        presenter?.askNotificationPermission()
        presenter?.checkEmail()
    }

    override fun initVersion() {
        val packageManager = this.packageManager
        val packageInfo = this.packageName?.let { packageManager?.getPackageInfo(it, 0) }
        binding.versionTxt.text = "GlobalXtreme © 2023 - v${packageInfo?.versionName}"
    }

    override fun initListener() {
        binding.passwordTxt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                presenter?.validateForm()

                false
            } else false
        }

        binding.showPasswordButton.setOnClickListener {
            presenter?.showHidePassword()
        }

        binding.signInButton.setOnClickListener {
            presenter?.validateForm()
        }
    }

    override fun showErrorFormValidation(editText: EditText, error: String) {
        editText.error = error
        editText.requestFocus()
    }

    override fun goToMain() {
        router?.goToMain()
    }

    override fun showSnackBar(error: String) {
        val snackBar = Snackbar.make(binding.mainView, error, Snackbar.LENGTH_SHORT)
        val snackBarView: View = snackBar.view
        val snackTextView =
            snackBarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        snackTextView.maxLines = 10
        snackBar.show()
    }

    override fun startPostLoading() {
        binding.signInLabel.text = ""
        binding.signInImage.visibility = View.INVISIBLE

        binding.signInButton.alpha = 0.5F
        binding.signInButton.isEnabled = false

        binding.postLoadingIcon.visibility = View.VISIBLE
    }

    override fun stopPostLoading() {
        binding.signInLabel.text = "Sign In"
        binding.signInImage.visibility = View.VISIBLE

        binding.signInButton.alpha = 1.0F
        binding.signInButton.isEnabled = true
        binding.postLoadingIcon.visibility = View.INVISIBLE
    }
}