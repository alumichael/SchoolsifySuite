package com.fbn.fbnquest.data.network

import com.schoolsify.suite.data.model.ValidationResponseHead
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiInterface {

    @GET("/school/lookup")
    suspend fun validateSchool(
        @Query("school_name") school_name:String
    ): ValidationResponseHead


}

