package com.globalxtreme.gxsalesdemo.contract

import com.globalxtreme.gxsalesdemo.model.datamodel.ComponentDataModel

interface ProspectFormComponentRepositoryInterface {
    fun getCompanyOfficeLocal(): MutableList<ComponentDataModel>
    fun saveCompanyOfficeLocal(data: ComponentDataModel)
    fun deleteAllCompanyOfficeLocal()
}