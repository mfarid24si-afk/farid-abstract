package com.example.farid_abstract

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farid_abstract.BinaDesa.EmailInputActivity
import com.example.farid_abstract.BinaDesa.WebViewActivity
import com.example.farid_abstract.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        enableEdgeToEdge()

        // 1. Aksi Tombol Login (Menggunakan View Binding)
        binding.btnLogin.setOnClickListener {
            val usernameInput = binding.inputEmail.text.toString()
            val passwordInput = binding.inputPassword.text.toString()

            if (usernameInput == passwordInput && usernameInput.isNotEmpty()) {
                val sharedPref = getSharedPreferences("farid@gmail.com", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.apply()

                val intent = Intent(this, WebViewActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Autentikasi Gagal")
                    .setMessage("Username dan Password tidak cocok atau kosong. Silakan coba lagi!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        // 2. Perbaikan: Aksi Tombol Register Gmail (Menggunakan View Binding)
        binding.btnRegisterGmail.setOnClickListener {
            val intent = Intent(this, EmailInputActivity::class.java)
            startActivity(intent)
        }

        // 3. Pengaturan Window Insets (Menggunakan View Binding)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
