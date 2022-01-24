package com.schoolsify.suite.data.network

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.clear
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import com.schoolsify.suite.Constant
import com.schoolsify.suite.Constant.Companion.LAUNCHED
import com.schoolsify.suite.Constant.Companion.LOGGED_IN
import com.schoolsify.suite.Constant.Companion.SCHOOL_LOGO_URL
import com.schoolsify.suite.Constant.Companion.SCHOOL_URL
import com.schoolsify.suite.Constant.Companion.USERPREF


import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Userpreference @Inject constructor( context: Context){

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(
            name = USERPREF
    )



    suspend fun setLoggedin(updated_beneficials: Boolean) {
        dataStore.edit { preferences ->
            preferences[LOGGED_IN] = updated_beneficials

        }
    }
    fun isLoggedIn()= dataStore.data.map { preferences -> preferences[LOGGED_IN]?:false}

    suspend fun setFirstLaunch(launch: Boolean) {
        dataStore.edit { preferences ->
            preferences[LAUNCHED] = launch

        }
    }
    fun isFirstLaunch()= dataStore.data.map { preferences -> preferences[LAUNCHED]?:false}


    suspend fun setSchoolUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[SCHOOL_URL] = url

        }
    }
    fun getSchoolUrl()= dataStore.data.map { preferences -> preferences[SCHOOL_URL]?:""}


    suspend fun setSchoolLogoUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[SCHOOL_LOGO_URL] = url

        }
    }
    fun getSchoolLogoUrl()= dataStore.data.map { preferences -> preferences[SCHOOL_LOGO_URL]?:""}



    //clear datastore
    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


}