package com.example.farid_abstract

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farid_abstract.BinaDesa.WebViewActivity
import com.example.farid_abstract.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi binding (WAJIB)
        binding = ActivityAuthBinding.inflate(layoutInflater)

        // 2. Pasang layoutnya (WAJIB)
        setContentView(binding.root)

        enableEdgeToEdge()

        // 3. Pasang Listener (Sekarang binding sudah aman digunakan)
        binding.btnLogin.setOnClickListener {
            val sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("isLogin", true) // Setel menjadi true agar sesi tersimpan
            editor.apply()

            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Pastikan R.id.main ada di layout activity_auth.xml kamu
        val mainView = findViewById<android.view.View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }

}