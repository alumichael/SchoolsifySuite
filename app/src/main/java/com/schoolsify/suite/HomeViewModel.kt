package com.schoolsify.suite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fbn.fbnquest.data.network.Resource
import com.schoolsify.suite.data.model.ValidationResponseHead
import com.schoolsify.suite.data.network.Userpreference
import com.schoolsify.suite.respository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val user_repository: UserRepository,
    val userpreference: Userpreference
        ) : ViewModel() {

    private val _getvalidation: MutableLiveData<Resource<ValidationResponseHead>> = MutableLiveData()
    val getvalidation: LiveData<Resource<ValidationResponseHead>> get() = _getvalidation



    fun getValidation(
        url:String
    ) =viewModelScope.launch{
        _getvalidation.value =
            user_repository.getValidation(url)
    }



    suspend fun saveSchoolUrl(url:String){
        user_repository.saveSchoolUrl(url)
    }

    suspend fun saveSchoolLogoUrl(url:String){
        user_repository.saveSchoolLogoUrl(url)
    }





}