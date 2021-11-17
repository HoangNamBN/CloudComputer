package com.cloudcomputer.kimychat.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.cloudcomputer.kimychat.R
import com.cloudcomputer.kimychat.core.base.BaseActivity
import com.cloudcomputer.kimychat.databinding.ActivitySplashBinding
import com.cloudcomputer.kimychat.ui.home.HomeActivity
import com.cloudcomputer.kimychat.ui.modulelogin.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {
    private var timer: CountDownTimer? = null
    private val TIME_SPLASH: Long = 2000

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override val mViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
            timer = object : CountDownTimer(TIME_SPLASH, 10) {
                override fun onTick(millisUntilFinished: Long) {
                    val progress: Float =
                        TIME_SPLASH * 1.0f / millisUntilFinished * 10
                    mViewBinding.showLoadDing.progress = progress.toInt()
                }

                override fun onFinish() {
                    gotoHome()
                    Log.d("khiem", "gotoHome().toString()")
                }
            }.start()
        }
        try {
            val versionName: String = packageManager.getPackageInfo(packageName, 0).versionName
            "v$versionName".also { mViewBinding.appVersionName.text = it }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun gotoHome() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            //chua login
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        } else {
            //da login
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}