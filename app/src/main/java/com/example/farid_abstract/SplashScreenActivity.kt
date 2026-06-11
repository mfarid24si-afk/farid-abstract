package com.example.farid_abstract

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.farid_abstract.BinaDesa.WebViewActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Handler

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycleScope.launch {
            delay(2000)

            // Cek apakah onboarding sudah pernah ditampilkan
            val onBoardingPref = getSharedPreferences(
                com.example.farid_abstract.onboarding.OnBoardingActivity.PREF_NAME,
                MODE_PRIVATE
            )
            val isOnBoardingDone = onBoardingPref.getBoolean(
                com.example.farid_abstract.onboarding.OnBoardingActivity.KEY_ONBOARDING_DONE,
                false
            )

            if (isOnBoardingDone) { //kalo baru janlup tambahin ! di kondidi if nya
                // Pertama kali install → tampilkan onboarding
                startActivity(Intent(this@SplashScreenActivity,
                    com.example.farid_abstract.onboarding.OnBoardingActivity::class.java))
            } else {
                // Sudah pernah onboarding → cek status login seperti biasa
                val sessionPref = getSharedPreferences("UserSession", MODE_PRIVATE)
                val isLogin = sessionPref.getBoolean("isLogin", false)
                if (isLogin) {
                    startActivity(Intent(this@SplashScreenActivity, WebViewActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
                }
            }
            finish()
        }

    }
}

