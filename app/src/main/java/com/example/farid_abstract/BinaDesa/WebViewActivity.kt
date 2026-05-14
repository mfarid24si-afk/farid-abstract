package com.example.farid_abstract.BinaDesa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farid_abstract.AuthActivity
import com.example.farid_abstract.R
import com.example.farid_abstract.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. SETUP KOMPONEN BAR NAVIGASI TOOLBAR
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Portal Bina Desa"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.toolbar.navigationIcon?.setTint(resources.getColor(android.R.color.white, theme))

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. KONFIGURASI ENGINE DASAR WEBVIEW (STANDBY)
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
        }

        // 3. AKSES MEMUAT LINK KETIKA TOMBOL DIKLIK
        binding.btnWebView.setOnClickListener {
            binding.webView.loadUrl("http://farid-peminjaman.alwaysdata.net/login")
        }

        // 4. PROSES CLEAR DATA SHAREDPREFERENCES SAAT LOGOUT
        binding.btnLogout.setOnClickListener {
            val sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("isLogin", false)
            editor.apply()

            // Arahkan kembali pengguna ke AuthActivity / LoginActivity Anda
            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    // 5. PENANGANAN KLIK PANAH BACK AGAR KEMBALI KE ACTIVITY SEBELUMNYA
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
