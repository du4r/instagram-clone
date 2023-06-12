package com.du4r.instagramclone.splash.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.du4r.instagramclone.R
import com.du4r.instagramclone.common.base.DependencyInjector
import com.du4r.instagramclone.common.extension.animationEnd
import com.du4r.instagramclone.databinding.ActivitySplashBinding
import com.du4r.instagramclone.login.view.LoginActivity
import com.du4r.instagramclone.main.view.MainActivity
import com.du4r.instagramclone.splash.Splash
import com.du4r.instagramclone.splash.presentation.SplashPresenter

class SplashActivity : AppCompatActivity(), Splash.View {
    private lateinit var binding: ActivitySplashBinding
    override lateinit var presenter: Splash.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = DependencyInjector.SplashRepository()
        presenter = SplashPresenter(this, repository)


        binding.splashImg.animate().apply {
            setListener(animationEnd{
                    presenter.authenticated()
            })
            duration = 1000
            alpha(1.0f)
            start()
        }
    }

    override fun goToMainScreen() {
        binding.splashImg.animate().apply {
            setListener(animationEnd{
                    val intent = Intent(baseContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            })
            duration = 1000
            startDelay = 1000
            alpha(0f)
            start()
        }
    }


    override fun goToLoginScreen() {
        binding.splashImg.animate().apply {
            setListener(animationEnd{
                val intent = Intent(baseContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            })
            duration = 1000
            startDelay = 1000
            alpha(0f)
            start()
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}