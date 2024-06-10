package com.globalxtreme.gxsales.helper.config

class Configuration {
    val BASE_URL = "https://dev.api.globalxtreme-gateway.net"

    val AUTH_URL = "/auth/mobile"
    val CUSTOMER_SUPPORT_URL = "/api/mobile/v1/customer-supports"
//    val INVENTORY_URL = "/api/mobile/v1/inventory-old"
    val INVENTORY_URL = "/api/mobile/v2/inventories"
    val NETWORK_URL = "/api/mobile/v1/network-servers"
    val SALES_URL = "/api/mobile/v1/sales-managements"
    val ENTERPRISE_URL = "/api/mobile/v1/enterprise-platforms"
    val BUSINESS_URL = "/api/mobile/v1/business-managements"

    //APK UPDATE
    val UPDATE_JSON_URL = "https://storage.globalxtreme-gateway.net/link/share/apk/gx-sales-update.json"

    //LOGIN
    val NOTIFICATION_APP_ID = "gx-sales-mobile"
    val LOGIN_URL = "/login"
    val PROFILE_URL = "/profile"
    val LOGOUT_URL = "/logout"
    val REFRESH_ACCESS_URL = "/refresh-access"
    val NOTIFICATION_TOKEN_URL = "/notifications/$NOTIFICATION_APP_ID/token"
    val NOTIFICATION_LIST_URL = "/notifications/$NOTIFICATION_APP_ID"
    val NOTIFICATION_READ_URL = "/notifications/$NOTIFICATION_APP_ID/read"

    //COMPONENT
    val WORK_ORDER_CATEGORY_URL = "/components/work-orders/categories"
    val WORK_ORDER_TYPE_URL = "/components/work-orders/types"
    val PACKAGE_URL = "/components/packages"
    val BUILDING_TYPE_URL = "/components/prospects/building-types"
    val CUSTOMER_URL = "/components/customers"
    val COMPANY_OFFICE_URL = "/company-offices"
    val ADD_ON_URL = "/components/package-add-ons"
    val NATIONALITY_URL = "/nationalities"

    //INVENTORY
    val ITEM_URL = "/items/warehouses"
    val TOOLBOX_URL = "/toolboxes"
    fun WORK_ORDER_LOADED_ITEM_URL(workOrderId: Int): String {
        return "/$workOrderId/toolboxes/items"
    }

    //PROSPECT
    val PROSPECT_URL = "/prospects"
    val LEAD_URL = "/leads"
    val CHECK_COVERAGE_URL = "/coverage/checkDetail"
    val DASHBOARD_URL = "/prospects/dashboard"
    fun PROSPECT_UPDATE_URL(prospectId: Int): String {
        return "/prospects/$prospectId/update"
    }
    fun PROSPECT_DETAIL_URL(prospectId: Int): String {
        return "/prospects/$prospectId/detail"
    }
    fun PROSPECT_NOTES_URL(prospectId: Int, locationId: Int): String {
        return "/prospects/$prospectId/service-location/$locationId/update-notes"
    }
    fun PROSPECT_UPDATE_SURVEY_PHOTO_URL(prospectId: Int, locationId: Int): String {
        return "/prospects/$prospectId/service-location/$locationId/update-survey"
    }
    fun PROSPECT_GET_PROFORMA_URL(locationId: Int, proformaId: Int): String {
        return "/prospects/service-location/$locationId/proforma/$proformaId"
    }
    fun PROSPECT_CREATE_PROFORMA_URL(locationId: Int): String {
        return "/prospects/service-location/$locationId/proforma"
    }
    fun PROSPECT_PROFORMA_APPLY_OPTION_URL(locationId: Int, proformaId: Int): String {
        return "/prospects/service-location/$locationId/proforma/${proformaId}/apply"
    }
    fun PROSPECT_GET_CONTRACT_URL(locationId: Int): String {
        return "/prospects/service-location/$locationId/contract"
    }
    fun PROSPECT_GET_JUSTIFICATION_URL(locationId: Int): String {
        return "/prospects/service-location/$locationId/justification"
    }
    fun PROSPECT_UPDATE_JUSTIFICATION_URL(locationId: Int, justificationId: Int): String {
        return "/prospects/service-location/$locationId/justification/$justificationId"
    }
    fun PROSPECT_CREATE_JUSTIFICATION_PAYMENT_URL(locationId: Int): String {
        return "/prospects/service-location/$locationId/justification-payment"
    }
    fun PROSPECT_UPDATE_JUSTIFICATION_PAYMENT_URL(locationId: Int, justificationId: Int): String {
        return "/prospects/service-location/$locationId/justification-payment/$justificationId"
    }

    //CONTRACT GENERATE LINK
    fun PROSPECT_CONTRACT_GENERATE_LINK_URL(locationId: Int): String {
        return "/prospects/service-location/$locationId/contract/generate-link"
    }

    //COUNTRY
    val COUNTRY_URL = "https://countriesnow.space/api/v0.1/countries/codes"

    val APIKeySuper = "AIzaSyCRV00e8AjA3r4W3269xvjChOupfVDBz6U"

    val ANDROID_CHANNEL_ID = "MYGL0b4LxtR3m34pPW9"

    val FORCE_RELOGIN = 0
}