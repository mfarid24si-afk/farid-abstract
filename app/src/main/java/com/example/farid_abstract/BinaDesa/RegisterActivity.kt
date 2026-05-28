package com.example.farid_abstract.BinaDesa // Sesuaikan package Anda

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farid_abstract.databinding.ActivityRegisterBinding // Sesuaikan nama binding Anda

class RegisterActivity : AppCompatActivity() {

    // Inisialisasi variabel binding
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout menggunakan View Binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tangkap data email dari Intent
        val emailFromIntent = intent.getStringExtra("EXTRA_EMAIL")

        if (!emailFromIntent.isNullOrEmpty()) {
            binding.etRegisterEmail.setText(emailFromIntent)
            binding.etRegisterEmail.isEnabled = false
        }

        // Aksi tombol submit dengan View Binding
        binding.btnSubmitRegister.setOnClickListener {
            val name = binding.etFullName.text.toString().trim()
            val password = binding.etRegisterPassword.text.toString().trim()

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap lengkapi semua data!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registrasi Berhasil untuk $name", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
