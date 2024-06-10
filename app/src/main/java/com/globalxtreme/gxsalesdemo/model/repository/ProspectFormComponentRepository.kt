package com.globalxtreme.gxsalesdemo.model.repository

import android.content.Context
import com.globalxtreme.gxsalesdemo.contract.ProspectFormComponentRepositoryInterface
import com.globalxtreme.gxsalesdemo.model.datamodel.ComponentDataModel
import com.globalxtreme.gxsalesdemo.model.db.CompanyOfficeDatabase
import java.util.ArrayList

class ProspectFormComponentRepository(val context: Context):
    ProspectFormComponentRepositoryInterface {

    override fun getCompanyOfficeLocal(): MutableList<ComponentDataModel> {
        val companyOfficeArr: MutableList<ComponentDataModel>?
        companyOfficeArr = ArrayList<ComponentDataModel>()

        val dbHelper = CompanyOfficeDatabase(context)
        val res = dbHelper.allData()

        if (res != null) {
            while (res.moveToNext()) {
                val list = ComponentDataModel(
                    res.getInt(0),
                    res.getString(1)
                )

                companyOfficeArr.add(list)
            }

            res.close()
            dbHelper.close()
        }

        return companyOfficeArr
    }

    override fun saveCompanyOfficeLocal(data: ComponentDataModel) {
        val dbHelper = CompanyOfficeDatabase(context)
        dbHelper.insertData(
            data.id,
            data.display
        )
        dbHelper.close()
    }

    override fun deleteAllCompanyOfficeLocal() {
        val dbHelper = CompanyOfficeDatabase(context)
        dbHelper.deleteAllRow()
        dbHelper.close()
    }

}