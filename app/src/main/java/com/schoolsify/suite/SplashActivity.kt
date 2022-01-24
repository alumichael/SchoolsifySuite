package com.schoolsify.suite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.schoolsify.suite.data.network.Userpreference
import com.schoolsify.suite.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var bottomAnim: Animation
    private lateinit var blinkAnim: Animation

    @Inject
    lateinit var userpreference: Userpreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Animations
        blinkAnim = AnimationUtils.loadAnimation(this, R.anim.blink)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        binding.logo.animation=bottomAnim
        binding.appTitle.animation = bottomAnim

        /*lifecycleScope.launch{
            delay(500)
            //binding.appTitle.animation = blinkAnim
        }*/

        userpreference.isLoggedIn().asLiveData().observe(this, Observer {

            Log.d("isLoggedIn",it.toString())
            lifecycleScope.launch{
                delay(4000)
                if (!it) startNewActivity(InitialWebSetup::class.java) else  startNewActivity(HomeActivity::class.java)
            }

        })
    }
}