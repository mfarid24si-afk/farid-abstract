package com.example.farid_abstract

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class Custom1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔥 HARUS PALING ATAS
        setContentView(R.layout.activity_custom1)

        // 🔹 baru boleh findViewById
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("judul")

        val tvJudul = findViewById<TextView>(R.id.tvJudul)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsi)

        val judul = intent.getStringExtra("judul")
        val deskripsi = intent.getStringExtra("deskripsi")

        tvJudul.text = judul
        tvDeskripsi.text = deskripsi
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}