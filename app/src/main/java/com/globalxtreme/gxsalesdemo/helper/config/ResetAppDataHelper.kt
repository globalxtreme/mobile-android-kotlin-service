package com.globalxtreme.gxsales.helper.config

import android.content.Context
import android.content.Intent
import com.globalxtreme.gxsalesdemo.model.prefs.PreferencesHelper
import com.globalxtreme.gxsalesdemo.view.activity.LoginActivity

class ResetAppDataHelper(private val context: Context) {

    fun clearPreferencesHelper() {
        val preferencesHelper = PreferencesHelper(context)
        preferencesHelper.userId = ""
        preferencesHelper.password = ""
        preferencesHelper.token = ""
        preferencesHelper.name = ""
        preferencesHelper.division = ""
        preferencesHelper.userImage = ""
        preferencesHelper.companyOffice = ""
    }

    fun clearDatabase() {
//        ProspectFormComponentRepository(context).deleteAllCompanyOfficeLocal()
//        ProspectFormComponentRepository(context).deleteAllBuildingTypeLocal()
//        ProspectFormComponentRepository(context).deleteAllCountryLocal()
//        ProspectFormComponentRepository(context).deleteAllNationalityLocal()
//
//        ProspectFormDraftRepository(context).deleteAllProspectDraftLocal()
//        ProspectFormLocationDraftRepository(context).deleteAllProspectLocationDraftLocal()
//        ProspectFormLocationRepository(context).deleteAllProspectFormLocationLocal()
//        ProspectFormRepository(context).deleteAllProspectFormLocal()
//        ProspectListRepository(context).deleteAllProspectListLocal()
    }

    fun logout() {
        val intent = Intent (context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

}