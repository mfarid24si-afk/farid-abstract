package com.example.farid_abstract

import Tugas_p4.LoginActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farid_abstract.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // tombol rumus
        binding.btnRumus.setOnClickListener {
            val intent = Intent(this, RumusActivity::class.java)
            intent.putExtra("judul", "Rumus Bangun Ruang")
            intent.putExtra("deskripsi", "Halaman perhitungan bangun ruang")
            startActivity(intent)
        }

        // tombol custom 1
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(this, Custom1Activity::class.java)
            intent.putExtra("judul", "About Us")
            intent.putExtra("deskripsi", "Aplikasi ini dibuat untuk membantu pengguna menghitung bangun ruang dengan mudah dan cepat.")
            startActivity(intent)
        }

        // tombol custom 2
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(this, Custom2Activity::class.java)
            intent.putExtra("judul", "Information")
            intent.putExtra("deskripsi", "Halaman ini berisi informasi mengenai fitur dan cara penggunaan aplikasi.")
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {

            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin logout?")

                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }

                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()

                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }

                .show()
        }
    }
}