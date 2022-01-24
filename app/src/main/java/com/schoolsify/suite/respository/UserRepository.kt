package com.schoolsify.suite.respository



import com.fbn.fbnquest.data.network.ApiInterface
import com.fbn.fbnquest.data.network.SafeApiCall
import com.schoolsify.suite.data.network.Userpreference
import javax.inject.Inject

class UserRepository @Inject constructor(
    private  val api: ApiInterface,
    private val userpreference: Userpreference
) : SafeApiCall {


    suspend fun getValidation(
        url:String
    ) =safeApiCall{
        api.validateSchool(url)
    }

    suspend fun saveSchoolUrl(url: String) {
        userpreference.setSchoolUrl(url)
    }

    suspend fun saveSchoolLogoUrl(url: String) {
        userpreference.setSchoolLogoUrl(url)
    }

}