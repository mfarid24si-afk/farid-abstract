package com.example.farid_abstract.BinaDesa // Sesuaikan package Anda

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farid_abstract.databinding.ActivityEmailInputBinding// Sesuaikan nama binding Anda
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EmailInputActivity : AppCompatActivity() {

    // Inisialisasi variabel binding
    private lateinit var binding: ActivityEmailInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout menggunakan View Binding
        binding = ActivityEmailInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Akses komponen langsung melalui objek binding
        binding.btnSubmitEmail.setOnClickListener {
            val emailText = binding.etGmailInput.text.toString().trim()

            if (emailText.isEmpty()) {
                showErrorDialog("Gagal", "Email tidak boleh kosong. Silakan masukkan email terlebih dahulu.")
                return@setOnClickListener
            }

            if (!emailText.endsWith("@gmail.com", ignoreCase = true)) {
                showErrorDialog("Domain Salah", "Pendaftaran ini wajib menggunakan akun email dengan domain @gmail.com.")
                return@setOnClickListener
            }

            val intent = Intent(this, RegisterActivity::class.java).apply {
                putExtra("EXTRA_EMAIL", emailText)
            }
            startActivity(intent)
        }
    }

    private fun showErrorDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Mengerti") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
