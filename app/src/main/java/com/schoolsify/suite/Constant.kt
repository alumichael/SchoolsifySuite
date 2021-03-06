package com.schoolsify.suite

import androidx.datastore.preferences.core.preferencesKey

open class Constant {
    companion object {
        val BASE_URL = "https://suitemanager.herokuapp.com/v1/api/"
        val USERPREF = "schoolsify_pref"
        val ACCESS_TOKEN = preferencesKey<String>("key_access_token")
        val LOGGED_IN = preferencesKey<Boolean>("loggin")
        val LAUNCHED = preferencesKey<Boolean>("lauched")
        val SCHOOL_URL = preferencesKey<String>("school_url")
        val SCHOOL_LOGO_URL = preferencesKey<String>("school_logo_url")



    }


}






























































































































































































































































































































































































































































































































































































































































































































































































































































































