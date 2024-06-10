package com.globalxtreme.gxsalesdemo.router

import android.content.Context
import android.content.Intent
import com.globalxtreme.gxsalesdemo.contract.LoginActivityInterface
import com.globalxtreme.gxsalesdemo.contract.LoginRouterInterface

class LoginRouter(_view: LoginActivityInterface, val context: Context):
    LoginRouterInterface {

    override fun goToMain() {
//        val intent = Intent(context, MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        context.startActivity(intent)
    }

}