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

            // 1. UBAH NAMA DARI "user_pref" MENJADI "UserSession" AGAR SINKRON
            val sharedPref = getSharedPreferences("UserSession", MODE_PRIVATE)

            // Default-nya harus false jika tidak ditemukan
            val isLogin = sharedPref.getBoolean("isLogin", false)

            if (isLogin) {
                // Jika TRUE (sudah login) -> ke Main / WebViewActivity
                startActivity(Intent(this@SplashScreenActivity, WebViewActivity::class.java))
            } else {
                // Jika FALSE (belum login) -> ke AuthActivity
                startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
            }
            finish()
        }

    }
}

