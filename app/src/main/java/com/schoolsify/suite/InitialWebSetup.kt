package com.schoolsify.suite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fbn.fbnquest.data.network.Resource
import com.schoolsify.suite.databinding.ActivityInitialWebSetupBinding
import com.schoolsify.suite.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class InitialWebSetup : AppCompatActivity() {

    private lateinit var binding: ActivityInitialWebSetupBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialWebSetupBinding.inflate(layoutInflater)
        setContentView( binding.root)

        viewModel.getvalidation.observe(this, {
            lifecycleScope.launch {
                binding.saveBtn.visible_gone(true)
                binding.progressBar.visible_gone(false)

                when (it) {
                    is Resource.Success -> {

                        try {

                            with(binding) {
                                viewModel.saveSchoolUrl(urlEditxt.text.toString())
                                it.value.data?.logoUrl?.let { it1 -> viewModel.saveSchoolLogoUrl(it1) }


                                snackbar("Sign in successfully",root)
                                startNewActivity(HomeActivity::class.java)
                            }
                        } catch (e: Exception) {

                            Log.i("Error", e.toString())
                            snackbar("An error occurred",binding.root)

                        }


                    }

                    is Resource.Failure -> {

                        if (it.errorCode == 400) {
                            snackbar("System could not recognize this school",binding.root)

                        } else {
                            snackbar("${it.errorCode}: An error occurred",binding.root)

                        }

                    }
                    else -> { }
                }




            }
        })

        binding.saveBtn.setOnClickListener {
            sendValidation(binding.urlEditxt.text.toString())

        }


    }

    private fun sendValidation(url: String){
        binding.saveBtn.visible_gone(false)
        binding.progressBar.visible_gone(true)

        viewModel.getValidation(url)
    }

    private fun createButtonObservable():Observable<String>{
        return Observable.create{emitter->




        }
    }
}