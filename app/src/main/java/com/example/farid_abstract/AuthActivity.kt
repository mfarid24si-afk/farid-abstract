package com.example.farid_abstract

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farid_abstract.BinaDesa.WebViewActivity
import com.example.farid_abstract.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        binding.btnLogin.setOnClickListener {
            // 1. Ambil teks yang diketik user dari EditText XML Anda
            // PENTING: Pastikan ID etUsername dan etPassword sudah sesuai dengan ID di activity_auth.xml Anda
            val usernameInput = binding.inputEmail.text.toString()
            val passwordInput = binding.inputPassword.text.toString()

            // 2. LOGIKA PENYARING: Cek apakah inputan cocok dan tidak kosong
            if (usernameInput == passwordInput && usernameInput.isNotEmpty()) {

                // Jika BENAR -> Simpan status masuk ke SharedPreferences
                val sharedPref = getSharedPreferences("farid", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.apply() // Kunci data sesi

                // Pindah ke halaman utama panel kontrol navigasi bawah
                val intent = Intent(this, WebViewActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                // Jika SALAH -> Tampilkan kotak peringatan (AlertDialog) agar gagal masuk
                AlertDialog.Builder(this)
                    .setTitle("Autentikasi Gagal")
                    .setMessage("Username dan Password tidak cocok atau kosong. Silakan coba lagi!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

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
